package springdatajpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import springdatajpa.entity.Dashboard;
import springdatajpa.entity.Dashboard_;
import springdatajpa.entity.Stage;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by wil on 05/03/2018.
 */
@Repository
interface DashboardDao extends JpaRepository<Dashboard, Long>, JpaSpecificationExecutor<Dashboard> {

    /**
     *
     */
    class DashboardSpecifications {

        /**
         *
         * @param name
         * @return
         */
        public static Specification<Dashboard> toPredicate(final String name) {
            return new Specification<Dashboard>() {
                @Override
                public Predicate toPredicate(Root<Dashboard> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.like(root.get(Dashboard_.name), name);
                }
            };
        }

    }

    /**
     * Simple of named query
     * @param dashboardId
     * @return
     */
    //Iterable<Stage> getDashboardStages(Long dashboardId);
     //   Query query = entityManager.createNamedQuery("dashboardStages");
       // query.setParameter("id", dashboardId);
        //return query.getResultList();
    //}

    /**
     *
     * @return
     */
    @Procedure(name = "dashboardWithStages")
    List<Dashboard> findWithStages();
}
