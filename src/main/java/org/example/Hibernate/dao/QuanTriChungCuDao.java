package org.example.Hibernate.dao;

import org.example.EntityAll.LichSuThayDoi;
import org.example.EntityAll.QuanTriChungCu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public class QuanTriChungCuDao {
    private SessionFactory sessionFactory;
    private Session session;
    public static QuanTriChungCuDao getInstance() {return new QuanTriChungCuDao(); };

    public boolean save(QuanTriChungCu quanTriChungCu){
        try {
            sessionFactory=Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            Serializable serializable= (Serializable) session.save(quanTriChungCu);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
            return (serializable!=null);
        } catch (Exception e) {
            System.out.println("Luu quan tri chung cu co loi");
            throw new RuntimeException(e);
        }
    }
}
