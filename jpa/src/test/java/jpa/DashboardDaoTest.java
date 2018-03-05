package jpa;

import jpa.dao.DashboardDao;
import jpa.entity.Dashboard;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by wil on 11/02/2018.
 */
public class DashboardDaoTest {

    private static EntityManager entityManager;
    private static DashboardDao dao;

    @BeforeClass
    public static void classSetUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myjava.persistence.unit");
        entityManager = emf.createEntityManager();

        dao = new DashboardDao(entityManager);
    }

    @AfterClass
    public static void classTearDown() {
        entityManager.close();
    }

    @Test
    public void save() {
        Dashboard dash = new Dashboard();
        dash.setName("Kanban");
        dash.setCreationDate(new Date());

        dao.save(dash);
    }
}
