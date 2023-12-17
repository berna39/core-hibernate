package com.terminator;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.terminator.domain.Person;

public class Clear {
    
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");
    public static void main(String[] args) {
        clearDemo();
    }

    private static void clearDemo() {
         EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Person p = Person.builder().name("Paul Pogba").subscriptionDate(new Date()).build();
        em.persist(p);

        /*
         * this will detach all objects present in the context and nothing
         * will happen on commit
         */
        em.clear();

        // NOTE: beafore you make a clear, make sure you make a flush() if you want to persist changes

        em.getTransaction().commit();
        em.close();
    }
}
