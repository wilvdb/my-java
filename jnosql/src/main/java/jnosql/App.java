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
import org.jnosql.diana.api.document.DocumentQuery;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static org.jnosql.diana.api.document.query.DocumentQueryBuilder.select;

public class App {

    public static void main(String[] args) throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();

        String bindIp = "localhost";
        int port = 27017;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.DEVELOPMENT)
                .timeout(new Timeout(60000L))
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .build();

        MongodExecutable mongodExecutable = null;

        Random random = new Random();
        Long id = random.nextLong();
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            mongodExecutable = starter.prepare(mongodConfig);
            MongodProcess mongod = mongodExecutable.start();

            Person person = new Person();
            person.setPhones(Arrays.asList("234", "432"));
            person.setName("Name");
            person.setId(id);

            DocumentTemplate documentTemplate = container.select(DocumentTemplate.class).get();
            Person saved = documentTemplate.insert(person);
            System.out.println("Person saved" + saved);


            DocumentQuery query = select().from("Person")
                    .where("id").eq(id).build();

            Optional<Person> personOptional = documentTemplate.singleResult(query);
            System.out.println("Entity found: " + personOptional);

        } finally {
            if (mongodExecutable != null)
                mongodExecutable.stop();
        }

    }
}
