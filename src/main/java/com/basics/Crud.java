package com.basics;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.basics.domain.Book;
import com.basics.domain.Song;

public class Crud {

    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");
    
    public static void main(String[] args) {
        // System.out.println(">>>>> ========= CREATE ======== <<<<<<<<");
        // createDemo();

        // System.out.println(">>>>> ========= RETRIEVE ======== <<<<<<<<");
        // findDemo();
        // gerReferenceDemo();

        // System.out.println(">>>>> ========= MERGE ======== <<<<<<<<");
        // createDemo();
        // mergeUpdateDemo();
        // mergeSaveDemo();

        System.out.println(">>>>> ========= REMOVE ======== <<<<<<<<");
        // removeTransientDemo();
        // removeDetachedDemo1();
        // removeDetachedDemo2();
        removeManagedDemo();

        emf.close();
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
        *  
        *  NOTE: when using autogenerated id (surrogate), for hibernate, if you have an id, 
        *        you should exist in the PCxt,
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

     private static void mergeSaveDemo() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        /*
         * in this case:
         *  1. Hibernate will hit the database to find a row with 111 as id
         *  2. if it's absent, the entity will we added to the PCxt, and then saved to the db
         *  3. if it's exists, the row will get an entity created in the PCxt, then properties
         *     will be merged and the persted entity will be returned
         */
        Book book = new Book("111", "Java EE 7");
        em.merge(book);

        Book otherBook = new Book("111", "Effective Java");
        em.merge(otherBook);

        em.getTransaction().commit();
        em.close();
    }

    private static void removeTransientDemo() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Book book = new Book("111", "Clean Code");
        /*
         * When removing an entity having an id, Hibernate will hit the database to retrieve
         * the entity so that it may remove if
         */
        em.remove(book); 

        /*
         * when removing an entity without id, Hibernate will consider it as transient,
         * and won't hit the db to retrieve it
         */
        Song song = new Song(null, "Shape of you", "Although my heart is falling too");
        em.remove(song);

        em.getTransaction().commit();
        em.close();
    }

    private static void removeDetachedDemo1() {
        /*
        * when removing detached objects, a IllegalArgumentException(Removing a detached instance)
        * will be thrown
        */
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Song song = new Song(null, "Baila", "baila X3");
        em.persist(song);

        em.detach(song);

        /*
        * removing detached objects will throw an exception
        */
        em.remove(song);

        em.getTransaction().commit();
        em.close();
    }

    private static void removeDetachedDemo2() {
        /*
        * when removing detached objects, a IllegalArgumentException(Removing a detached instance)
        * will be thrown
        */
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Song song = new Song(null, "Baila", "baila X3");
        em.persist(song);
        
        em.getTransaction().commit();
        em.close(); // when closing the Entity manager, all objects are being detached

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // song is detached koz it's hasn't been created by the current EntityManager
        em.remove(song);

        em.getTransaction().commit();
        em.close();
    }

    private static void removeManagedDemo() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Song song = new Song(null, "Baila", "baila X3");
        em.persist(song);

        em.remove(song);

        em.getTransaction().commit();
        em.close();
    }
}
