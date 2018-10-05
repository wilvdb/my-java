package jnosql;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.Timeout;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import jnosql.entity.Person;
import org.jnosql.artemis.document.DocumentTemplate;
import org.jnosql.artemis.key.KeyValueTemplate;
import org.jnosql.diana.api.document.DocumentQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.embedded.RedisServer;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static org.jnosql.diana.api.document.query.DocumentQueryBuilder.select;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();

        String bindIp = "localhost";
        int port = 27017;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.DEVELOPMENT)
                .timeout(new Timeout(60000L))
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .build();

        RedisServer redisServer = null;
        MongodExecutable mongodExecutable = null;

        Random random = new Random();
        Long id = random.nextLong();

        LOGGER.info("Start CDI container");
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            LOGGER.info("Start MongoDB....");
            mongodExecutable = starter.prepare(mongodConfig);
            MongodProcess mongod = mongodExecutable.start();
            LOGGER.info("MongoDB started");

            LOGGER.info("Start Redis....");
            redisServer = new RedisServer(6379);
            redisServer.start();
            LOGGER.info("Redis started");

            Person person = new Person();
            person.setPhones(Arrays.asList("234", "432"));
            person.setName("Name");
            person.setId(id);

            LOGGER.info("Get DocumentTemplate from container");
            DocumentTemplate documentTemplate = container.select(DocumentTemplate.class).get();
            Person saved = documentTemplate.insert(person);
            LOGGER.info("Person saved into MongoDB");

            LOGGER.info("Create query for MongoDB");
            DocumentQuery query = select().from("Person")
                    .where("id").eq(id).build();

            Optional<Person> personOptional = documentTemplate.singleResult(query);
            LOGGER.info("Entity found {}", personOptional);

            LOGGER.info("Get KeyValueTemplate from container");
            KeyValueTemplate template = container.select(KeyValueTemplate.class).get();
            Person userSaved = template.put(person);
            LOGGER.info("Person saved into Redis");
            Optional<Person> user = template.get(id, Person.class);
            LOGGER.info("Entity found {}", user);

        } finally {
            if (mongodExecutable != null) {
                mongodExecutable.stop();
            }

            if (redisServer != null) {
                redisServer.stop();
            }
        }

    }
}
