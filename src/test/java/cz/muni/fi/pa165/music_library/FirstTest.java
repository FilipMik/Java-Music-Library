package cz.muni.fi.pa165.music_library;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class FirstTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    public void test() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
    }
}
