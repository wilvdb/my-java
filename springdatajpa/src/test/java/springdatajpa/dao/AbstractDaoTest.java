package springdatajpa.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by wil on 07/03/2018.
 */
public class AbstractDaoTest {

    protected static EntityManager entityManager;
    private static DashboardDao dao;

    protected static void classSetUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my.persistence.unit");
        entityManager = emf.createEntityManager();
    }

    @AfterClass
    public static void classTearDown() {
        entityManager.close();
    }

    @Before
    public void setUp() {
        entityManager.getTransaction().begin();
    }

    @After
    public void tearDown() {
        entityManager.getTransaction().commit();
    }

}
