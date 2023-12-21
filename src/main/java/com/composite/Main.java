package com.composite;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.composite.domain.Order;
import com.composite.domain.OrderId;

public class Main {
    
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Order order = new Order();
        order.setAmount(12.34);
        order.setDeliveryAddress("11/13 Rue du Maréchal, Lille");

        OrderId orderId = new OrderId("LN123", "DLN123");
        order.setOrderId(orderId);

        em.persist(order);

        Order order1 = new Order();
        order1.setAmount(52.66);
        order1.setDeliveryAddress("11/13 Rue du Maréchal, Lille");

        OrderId orderId1 = new OrderId("LN155", "DLN123");
        order1.setOrderId(orderId1);

        em.persist(order1);

        em.getTransaction().commit();
        em.close();
    }
}
