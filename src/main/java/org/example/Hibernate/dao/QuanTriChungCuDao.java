package org.example.Hibernate.dao;

import org.example.EntityAll.QuanTriChungCu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuanTriChungCuDao {
    private SessionFactory sessionFactory;
    private Session session;
    public static QuanTriChungCuDao getInstance() {return new QuanTriChungCuDao(); };

    public boolean save(QuanTriChungCu quanTriChungCu){
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            Serializable serializable= (Serializable) session.save(quanTriChungCu);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
            return (serializable!=null);
        } catch (Exception e) {
            System.out.println("Luu quan tri chung cu co loi");
            throw new RuntimeException(e);
        }
    }

    public List<QuanTriChungCu> selectAll() {
        List<QuanTriChungCu> quanTriChungCus = new ArrayList<>();
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            quanTriChungCus = session.createQuery("FROM QuanTriChungCu", QuanTriChungCu.class).getResultList();
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return quanTriChungCus;
    }

    public void deleteQuanTriChungCu(int id) {
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM QuanTriChungCu "  + "WHERE id = :id").setParameter("id", id);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
