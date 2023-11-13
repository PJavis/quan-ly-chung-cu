package org.example.Hibernatedao;

import org.example.EntityAll.QuanTriChungCu;
import org.example.Function.Delete;
import org.example.Function.Save;
import org.example.Function.SelectAll;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuanTriChungCuDao implements Save<QuanTriChungCu>, Delete, SelectAll {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session;
    public static QuanTriChungCuDao getInstance() {return new QuanTriChungCuDao(); }

    @Override
    public boolean save(QuanTriChungCu quanTriChungCu) {
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            Serializable serializable= (Serializable) session.save(quanTriChungCu);
            Hibernate.closeSession(session);

            return (serializable!=null);
        } catch (Exception e) {
            System.out.println("Luu quan tri chung cu co loi");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<?> selectAll() {
        List<QuanTriChungCu> quanTriChungCus;
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            quanTriChungCus = session.createQuery("FROM QuanTriChungCu", QuanTriChungCu.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return quanTriChungCus;
    }

    @Override
    public void delete(int id) {
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM QuanTriChungCu "  + "WHERE id = :id").setParameter("id", id).executeUpdate();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
