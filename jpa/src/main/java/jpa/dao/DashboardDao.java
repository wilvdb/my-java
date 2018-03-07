package jpa.dao;

import jpa.entity.Dashboard;
import jpa.entity.Stage;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by wil on 05/03/2018.
 */
public class DashboardDao implements Dao<Long, Dashboard> {

    private EntityManager entityManager;

    public DashboardDao(EntityManager entityManager) { this.entityManager = entityManager; }

    @Override
    public Dashboard save(Dashboard entity) {
        if(entity.getId() == null) {
            entityManager.persist(entity);
        }

        return entity;
    }

    @Override
    public Iterable<Dashboard> findAll() {
        Query query = entityManager.createQuery("select d from Dashboard d", Dashboard.class);
        return query.getResultList();
    }

    @Override
    public Dashboard findById(Long id) {
        Query query = entityManager.createQuery("select d from Dashboard d where d.id = :id", Dashboard.class);
        query.setParameter("id", id);
        return (Dashboard) query.getSingleResult();
    }

    /**
     * Sample of named query
     * @param dashboardId
     * @return
     */
    public Iterable<Stage> getDashboardStages(Long dashboardId) {
        Query query = entityManager.createNamedQuery("dashboardStages");
        query.setParameter("id", dashboardId);
        return query.getResultList();
    }

    @Override
    public void deleteAll() {
        Iterable<Dashboard> dashboards = findAll();
        for (Dashboard dashboard : dashboards) {
            entityManager.remove(dashboard);
        }
    }
}
