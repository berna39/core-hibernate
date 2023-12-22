package com.composite;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.composite.domain.Order;
import com.composite.domain.OrderId;
import com.composite.domain.Payment;

public class Main {
    
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pg-datasource");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Order order = new Order();
        order.setPayments(new ArrayList<>());
        order.setAmount(12.34);
        order.setDeliveryAddress("11/13 Rue du Maréchal, Lille");

        OrderId orderId = new OrderId("LN123", "DLN123");
        order.setOrderId(orderId);

        Payment payment = new Payment(null, 12.34, order);
        order.getPayments().add(payment);

        em.persist(order);
        em.persist(payment);

        Order order1 = new Order();
        order1.setPayments(new ArrayList<>());
        order1.setAmount(52.66);
        order1.setDeliveryAddress("11/13 Rue du Maréchal, Lille");

        OrderId orderId1 = new OrderId("LN155", "DLN123");
        order1.setOrderId(orderId1);

        Payment payment1 = new Payment(null, 52.66, order);
        order1.getPayments().add(payment1);

        em.persist(order1);
        em.persist(payment1);

        em.getTransaction().commit();
        em.close();
    }
}
