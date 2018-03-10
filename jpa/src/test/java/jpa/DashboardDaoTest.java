package jpa;

import jpa.dao.DashboardDao;
import jpa.entity.Dashboard;
import jpa.entity.Stage;
import org.assertj.core.api.Assertions;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

/**
 * Created by wil on 11/02/2018.
 */
public class DashboardDaoTest extends AbstractDaoTest {

    private static DashboardDao dao;

    @BeforeClass
    public static void classSetUp() {
        AbstractDaoTest.classSetUp();
        dao = new DashboardDao(entityManager);
    }

    @Test
    public void basic() {
        Dashboard dash = new Dashboard();
        dash.setName("Kanban");
        dash.setCreationDate(new Date());

        dao.save(dash);

        Assertions.assertThat(dash.getId()).isNotNull();

        Iterable<Dashboard> dashboards = dao.findAll();
        Assertions.assertThat(dashboards).hasSize(3);

        Dashboard d = dao.findById(dash.getId());
        Assertions.assertThat(d.getName()).isEqualTo(dash.getName());
    }

    @Test
    public void dasboard_stages() {
        Dashboard dash = new Dashboard();
        dash.setName("Kanban2");
        dash.setCreationDate(new Date());

        dao.save(dash);

        Stage stage1 = new Stage();
        stage1.setName("dev");
        stage1.setWorkInProgress(2);

        Set<Stage> stages = new HashSet<>();
        stages.add(stage1);
        dash.setStages(stages);

        Iterable<Stage> dashboardStages = dao.getDashboardStages(dash.getId());
        Assertions.assertThat(dashboardStages).hasSize(1);
        Stage s = dashboardStages.iterator().next();
        Assertions.assertThat(s.getName()).isEqualTo(stage1.getName());
    }

    @Test
    public void dashboard_like_name() {
        Dashboard dash = new Dashboard();
        dash.setName("Kanban3");
        dash.setCreationDate(new Date());

        dao.save(dash);

        Iterable<Dashboard> dashboards = dao.findLikeName("K%");
        Assertions.assertThat(dashboards).hasSize(1);
    }
}
