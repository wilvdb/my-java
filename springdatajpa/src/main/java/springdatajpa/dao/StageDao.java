package springdatajpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springdatajpa.entity.Stage;

/**
 * Created by wil on 18/03/2018.
 */
@Repository
public interface StageDao extends JpaRepository<Stage, Long> {

    @Query("select d.stages from Dashboard d where d.id = :id")
    Iterable<Stage> getDashboardStages(@Param("id") Long dashboardId);
}
