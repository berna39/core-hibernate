package com.terminator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.terminator.domain.Song;

public class Crud {

    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");
    
    public static void main(String[] args) {
        // System.out.println(">>>>> ========= CREATE ======== <<<<<<<<");
        // createDemo();

        // System.out.println(">>>>> ========= RETRIEVE ======== <<<<<<<<");
        // findDemo();
        // gerReferenceDemo();

        System.out.println(">>>>> ========= Merge ======== <<<<<<<<");
        createDemo();
        mergeUpdateDemo();
    }

    private static void createDemo() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // this song entity is transient
        Song song = new Song(null, "Shape of You", "huh wah huh wah huh wah");

        /*
         * 1. firstly, an ID is being assigned to the entity, using db sequence in this case
         * 2. the song moves from transient to the first-level-cache || persistance context
         */
        em.persist(song);
        /*
         *  this will trigger an UPDATE request to the db koz 
         *  the entity manager keep on tracking every persistent 
         *  entity update persists it to the database
         */
        song.setScript("bboy on the beat"); 
        em.getTransaction().commit();
        em.close();
    }

    private static void findDemo() {
        EntityManager em = emf.createEntityManager();
        /*
         * When  using find, Hibernate gonna hit the db to retrieve the object if exists
         */
        Song res = em.find(Song.class, 1L);
        System.out.println(res.toString());
        em.close();
    }

    private static void gerReferenceDemo() {
        EntityManager em = emf.createEntityManager();
        /*
         * When  using geReference, Hibernate gonna provide a proxy until we need to use 
         * the object, then it's gonna hit the db to retrieve it
         */
        Song res = em.getReference(Song.class, 1L);
        System.out.println(res.toString());
        em.close();
    } 

    private static void mergeUpdateDemo() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Song detachedSong = em.find(Song.class, 1L);
        em.detach(detachedSong); // at this moment, this entity is no longer persisted

        detachedSong.setTitle("Bonne journée");   
        /*
        * and if we do em.persist(detachedSong), an exception will thrown because:
        *    1. Hibernate does not save any entity out of the PCxt
        *    2. When trying to move detachedSong to the PCxt, as the entityuses a SURROGATE KEY(generated id)
                an Id should assigned first, 
        *       as it's already has an id, Hibernate will try to find it in the PCxt and won't find it -> 
        *       and throw the exception
        */

       
        /*
        *   However, when doing merge, Hibernate will hit the db and retrieve an entity having
        *   the same id as detachedSong.getId(), and put it in the PCxt, merge updates and return
        *   a persisted object
        */
        Song persistedSong = em.merge(detachedSong); 
        
        persistedSong.setTitle("wote"); // this will me persisted

        em.getTransaction().commit();
        em.close();
    }
}
