package org.example.Hibernatedao;

import org.example.EntityAll.HoKhau;
import org.example.Function.Delete;
import org.example.Function.Save;
import org.example.Function.SelectAll;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HoKhauDao implements Save<HoKhau>, Delete, SelectAll {
 private SessionFactory sessionFactory = null;
 private  Session session = null;

    public static HoKhauDao getInstance() {
        return new HoKhauDao();
    }

    public boolean save(HoKhau hoKhau){
       try {
           sessionFactory=Hibernate.getSessionFactory();
           session=Hibernate.getSession(sessionFactory);
           Serializable serializable = (Serializable) session.save(hoKhau);
           Hibernate.closeSession(session);
           Hibernate.closeSessionFactory(sessionFactory);
           return (serializable!=null);
       } catch (Exception e) {
           System.out.println("Luu ho khau co loi");
           throw new RuntimeException(e);
       }
    }

    public List<HoKhau> selectAll() {
        List<HoKhau> hoKhaus = new ArrayList<>();
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            hoKhaus = session.createQuery("FROM HoKhau", HoKhau.class).getResultList();
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return hoKhaus;
    }

    public void delete(int id) {
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM HoKhau "  + "WHERE id = :id").setParameter("id", id);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
