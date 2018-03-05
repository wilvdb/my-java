package jpa.dao;

/**
 * Created by wil on 05/03/2018.
 */
public interface Dao<K, E> {

    E save(E entity);

    E findById(K id);

    Iterable<E> findAll();
}
