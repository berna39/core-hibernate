package com.terminator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.terminator.domain.Book;

public class PushChange {
    
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");

    public static void main(String[] args) {
        // pushChangeDemo2();
        pushChangeDemo2();
    }

    private static void pushChangeDemo1() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Book book = new Book("1111", "clean code");
        em.persist(book); // added to the cache

        /*
         * this query won't directly hit the database, it's just a declaration
         */
        TypedQuery<Book> query = em.createQuery("from Book", Book.class);

        book.setTitle("cracking a coding interview");
        /*
         *  the db will be hitted when calling the getResultList(), and before it, all updates will be pushed
         *  to the database (insert and upda in this case)
         *  even though the commit has not been done
         */
        System.out.println(query.getResultList());
        

        em.getTransaction().commit();
        em.close();
    }

    private static void pushChangeDemo2() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Book book = new Book("1111", "clean code");
        em.persist(book); // added to the cache

        book.setTitle("cracking a coding interview");

        em.flush();

        em.getTransaction().commit();
        em.close();
    }
}
