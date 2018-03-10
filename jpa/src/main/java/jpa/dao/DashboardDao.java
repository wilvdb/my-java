package jpa.dao;

import jpa.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by wil on 05/03/2018.
 */
public class DashboardDao implements Dao<Long, Dashboard> {

    private EntityManager entityManager;

    /**
     *
     * @param entityManager
     */
    public DashboardDao(EntityManager entityManager) { this.entityManager = entityManager; }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public Dashboard save(Dashboard entity) {
        if(entity.getId() == null) {
            entityManager.persist(entity);
        }

        return entity;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterable<Dashboard> findAll() {
        Query query = entityManager.createQuery("select d from Dashboard d", Dashboard.class);
        return query.getResultList();
    }

    /**
     *
     * @param id
     * @return
     */
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

    /**
     *
     */
    @Override
    public void deleteAll() {
        Iterable<Dashboard> dashboards = findAll();
        for (Dashboard dashboard : dashboards) {
            entityManager.remove(dashboard);
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public Iterable<Dashboard> findLikeName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Dashboard> criteria = builder.createQuery(Dashboard.class);
        Root<Dashboard> dashboardRoot = criteria.from(Dashboard.class);
        criteria.select(dashboardRoot);
        criteria.where(builder.like(dashboardRoot.get(Dashboard_.name), name));

        return entityManager.createQuery(criteria).getResultList();
    }
}
