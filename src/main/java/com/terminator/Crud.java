package com.terminator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.terminator.domain.Song;

public class Crud {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        System.out.println(">>>>> ====== CREATE ======== <<<<<<<<");

        // this song is transient
        Song song = Song.builder().title("La Pluie").script("Il fait beau").build();

        // the song moves from transient to the first-level-cache || persistance context
        em.persist(song);
        /*
         *  this will trigger an UPDATE request to the db koz 
         *  the entity manager keep on tracking every persistent 
         *  entity update persists it to the database
         */
        song.setScript("tatarira"); 

        em.getTransaction().commit();
    }
}
