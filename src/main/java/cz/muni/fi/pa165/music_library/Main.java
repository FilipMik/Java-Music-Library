package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.data.entities.Artist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;


public class Main {

    private static EntityManagerFactory emf;

    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("default");
        System.out.println("Let's party!!");

        EntityManager emWork = emf.createEntityManager();
        emWork.getTransaction().begin();
        Artist artist = new Artist();
        artist.setArtistId(1L);
        artist.setName("PETER");
        artist.setArtistInfo("INFO");
        artist.setBirthDate(new Date());
        emWork.persist(artist);

        emWork.getTransaction().commit();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Artist> categories = em.createQuery(
                "select c from Artist c order by c.name", Artist.class)
                .getResultList();


        System.out.println("TEST: " + categories.get(0).getName());

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}
