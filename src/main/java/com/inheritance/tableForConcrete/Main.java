package com.inheritance.tableForConcrete;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.inheritance.tableForConcrete.domain.Checking;
import com.inheritance.tableForConcrete.domain.Saving;

public class Main {
    
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Saving saving = new Saving(1200, 0.2);
        em.persist(saving);

        Checking checking = new Checking (320, 250);
        em.persist(checking);

        em.getTransaction().commit();
        em.close();
    }
}
