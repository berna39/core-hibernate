package com.terminator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.terminator.domain.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( ">> ======== HIBERNATE JPA CORE ========= <<" );

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("firstDemo");  
        EntityManager em = factory.createEntityManager();  
        EntityTransaction transaction = em.getTransaction();  
        
        transaction.begin();

        em.persist(Person.builder().name("Shango Joseph").age(25).build());

        transaction.commit();
        em.close();
    }
}
