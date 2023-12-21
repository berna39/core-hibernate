package com.secondaryTable;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.secondaryTable.domain.Student;

public class Main {

    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Student student1 = new Student(null, "Shango", "3966", "CS", LocalDate.of(2015, 10, 15));
        Student student2 = new Student(null, "Daniel", "3687", "PH", LocalDate.of(2014, 10, 15));

        em.persist(student1);
        em.persist(student2);
        
        em.getTransaction().commit();
        em.close();
    }
}
