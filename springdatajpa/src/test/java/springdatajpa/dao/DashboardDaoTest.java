package springdatajpa.dao;

import springdatajpa.entity.Dashboard;
import springdatajpa.entity.Stage;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wil on 11/02/2018.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/application-context.xml")
@Transactional
public class DashboardDaoTest {

    @Autowired
    private DashboardDao dao;

    @Test
    public void basic() {
        Dashboard dash = new Dashboard();
        dash.setName("Kanban");
        dash.setCreationDate(new Date());

        dao.save(dash);

        Assertions.assertThat(dash.getId()).isNotNull();

        Iterable<Dashboard> dashboards = dao.findAll();
        Assertions.assertThat(dashboards).hasSize(1);

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

    @Test
    public void dashboard_with_stages() {
        Dashboard dash = new Dashboard();
        dash.setName("Kanban2");
        dash.setCreationDate(new Date());

        Stage stage1 = new Stage();
        stage1.setName("dev");
        stage1.setWorkInProgress(2);

        Set<Stage> stages = new HashSet<>();
        stages.add(stage1);
        dash.setStages(stages);
        
        dao.save(dash);

        Iterable<Dashboard> dashboards = dao.findWithStages();

        Assertions.assertThat(dashboards).hasSize(1);
    }
}
