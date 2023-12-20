package com.oneToOne;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.oneToOne.domain.Car;
import com.oneToOne.domain.Engine;

public class Main {
    
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Car car = new Car();
        car.setMark("Opel");
        car.setModel("Zignia");

        Engine engine = new Engine();
        engine.setVin("OP34-243234-243");
        engine.setCar(car);

        car.setEngine(engine);

        em.persist(car);
        em.remove(car);

        em.getTransaction().commit();
        em.close();
    }
}
