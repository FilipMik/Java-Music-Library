package cz.muni.fi.pa165.music_library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FirstTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void test() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
    }
}
