package com.basics;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.basics.domain.Book;
import com.basics.domain.Person;
import com.basics.domain.Team;

public class Cache {
    
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");

    public static void main(String[] args) {
        
        // System.out.println("======= INSERT HELD IN CACHE ========");
        // insertHeldInCacheDemo1();
        // insertHeldInCacheDemo2();

        // System.out.println("======= RETRIEVIAL USES CACHE ========");
        // retrievalUsesCache();

        // System.out.println("======= UPDATE HELD IN CACHE ========");
        // updateHeldInCacheDemo1();
        // updateHeldInCacheDemo2();

        System.out.println("======= REMOVE HELD IN CACHE ========");
        removeHeldInCache();

        emf.close();
    }

    private static void insertHeldInCacheDemo1() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Person p = Person.builder().name("Paul Pogba").subscriptionDate(new Date()).build();

        /*
         *  this won't be inserted directly, Hibernate will only hit the db to retrieve the id
         *  as the generation strategy is sequence in this case -> p will be held in cache until the next commit
         */
        em.persist(p);

        em.getTransaction().commit();
        em.close();
    }

    private static void insertHeldInCacheDemo2() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Team t = Team.builder().name("Arsenal").Country("England").build();
        /*
         * in this case, the insert will be performed directly because the id generation strategy is IDENTITY
         */
        em.persist(t);

        em.getTransaction().commit();
        em.close();
    }

    public static void retrievalUsesCache() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(Team.builder().name("LOSC").Country("France").build());
        /*
         * this row is already in the cache, no need to hit the db
         */
        em.find(Team.class, 1L);
         /*
         * this is not in the cache, the db will be hited in this case
         */
        em.find(Team.class, 2L);

        em.getTransaction().commit();

        em.close();
    }

    private static void updateHeldInCacheDemo1() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Person p = Person.builder().name("Paul Pogba").subscriptionDate(new Date()).build();

        /*
         *  1. when updating a persisted enity, Hibername will wait until the the query or commit to
         *     merge all updated and make one update query
         *  2. as the id is generated by a sequence in this case, both insert and update queries will
         *     be performed on commit()
         */
        em.persist(p);

        p.setName("Aurelien Tchouameni");

        p.setName("Jamie Vardy");
        p.setDob(LocalDate.of(1986, 1, 15));

        em.getTransaction().commit();
        em.close();
    }

    private static void updateHeldInCacheDemo2() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Team team = Team.builder().name("VClub").Country("DRC").build();

        /*
         * when updating a persisted enity, Hibername will wait until the the query or commit to
         * merge all updated and make one update query
         * 
         * NOTE: updates are hold in the cache,
         */
        em.persist(team);

        team.setName("Vita Club");

        em.getTransaction().commit();
        em.close();
    }

    private static void removeHeldInCache() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(new Book("1111", "Effective Java"));

        /*
         * this is already in the persistance ctx....no need to hit the database
         */
        Book book = em.find(Book.class, "1111");
        System.out.println(em.contains(book)); // true -> already in cache

        /*
         * this wont trigger an update request, koz hibernate don't need to update an object 
         * that will somehow be deleted...efficiency
         */
        book.setTitle("head first Java");

        em.remove(book);
        System.out.println(em.contains(book)); // false -> has been removed from cache

        em.getTransaction().commit();
        em.close();
    }
}
