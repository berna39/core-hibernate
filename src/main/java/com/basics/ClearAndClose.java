package com.basics;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.basics.domain.Person;

public class ClearAndClose {
    
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
        /*
         *  the colse method closes an entity manager to release its persistence context and other resources.
         *  After calling close, the application must not invoke any further methods on the EntityManager
         *  instance except for getTransaction and isOpen, or the IllegalStateException will be thrown. 
         */
        em.close();
    }
}
