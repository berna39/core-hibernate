package com.basics;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.basics.domain.Person;
import com.basics.domain.Song;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( ">> ======== HIBERNATE JPA CORE ========= <<" );

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pg-datasource");  
        EntityManager em = factory.createEntityManager();  
        EntityTransaction transaction = em.getTransaction();  
        
        transaction.begin();

        em.persist(Person.builder()
            .name("Shango Joseph")
            .subscriptionDate(new Date())
            .dob(LocalDate.of(1998, 1, 15))
            .build());
        em.persist(Person.builder()
            .name("Salaam palya")
            .subscriptionDate(new Date())
            .dob(LocalDate.of(1996, 1, 15))
            .build());
        em.persist(Person.builder()
            .name("Don Jazzy")
            .dob(LocalDate.of(1978, 1, 15))
            .subscriptionDate(new Date())
            .build());

        em.persist(new Song(1L, "Shape of You", "huh wah huh wah huh wah"));

        transaction.commit();

        /*
         * when creating a query, we should use the Java Persistance Query Language
         * and we should either use the EntityName (Person in this case)
         * or if the @Entity annotation has a name param, we should use the name value (people in this case) 
         * 
         * note: we can also use Hibernate Query Language
         */
        List<Person> people = em.createQuery("from person").getResultList();
        System.out.println(people.get(0));

        /*
         *  we can also create a native query, and use the @Table's name param value as table
         *  or the @entity's name param value
         */
        List<Person> peopleNative = em.createNativeQuery("select * from people").getResultList();
        System.out.println(peopleNative.size());

        em.close();
        factory.close(); 
    }
}
