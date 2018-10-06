package jnosql.repository;

import jnosql.entity.Person;
import org.jnosql.artemis.Repository;

public interface PersonRepository extends Repository<Person, Long> {
}
