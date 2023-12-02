package com.terminator;

import java.util.List;

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

        /*
         * When creating a query, we should use the Java Persistance Query Language
         * and we should either use the EntityName (Person in this case)
         * or if the @Entity annotation has a name param, we should use the name value (people in this case) 
         * 
         * Note: we can also use Hibernate Query Language
         */
        List<Person> people = em.createQuery("from person").getResultList();
        System.out.println(people.get(0));

        /*
         * we can also create a native query, and use the @Table's name param value as table
         *  or the @entity's name param value
         */
        List<Person> peopleNative = em.createNativeQuery("select * from people").getResultList();
        System.out.println(peopleNative.size());

        em.close();
    }
}
