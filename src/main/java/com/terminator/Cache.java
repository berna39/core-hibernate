package com.terminator;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.terminator.domain.Person;
import com.terminator.domain.Team;

public class Cache {
    
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");

    public static void main(String[] args) {
        
        insertHeldInCacheDemo1();
        insertHeldInCacheDemo2();

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
}
