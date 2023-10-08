package org.example.Hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class Hibernate {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();

        sessionFactory.close();
    }
}
