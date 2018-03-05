package jpa.dao;

import jpa.entity.Dashboard;

import javax.persistence.EntityManager;

/**
 * Created by wil on 05/03/2018.
 */
public class DashboardDao implements Dao<Long, Dashboard> {

    private EntityManager entityManager;

    public DashboardDao(EntityManager entityManager) { this.entityManager = entityManager; }

    @Override
    public Dashboard save(Dashboard entity) {
        entityManager.getTransaction().begin();

        if(entity.getId() == null) {
            entityManager.persist(entity);
        }
        entityManager.getTransaction().commit();

        return entity;
    }
}
