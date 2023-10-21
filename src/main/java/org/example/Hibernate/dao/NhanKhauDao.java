package org.example.Hibernate.dao;

import org.example.EntityAll.NhanKhau;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NhanKhauDao {
    private SessionFactory sessionFactory;
    private Session session;
    public static NhanKhauDao getInstance() {return new NhanKhauDao(); };

    public boolean save(NhanKhau nhanKhau){
        try {
            sessionFactory=Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            Serializable serializable= (Serializable) session.save(nhanKhau);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
            return (serializable!=null);
        } catch (Exception e) {
            System.out.println("Luu nhan khau co loi");
            throw new RuntimeException(e);
        }
    }
    public List<NhanKhau> selectAll() {
        List<NhanKhau> nhanKhaus = new ArrayList<>();
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            nhanKhaus = session.createQuery("FROM nhanKhau", NhanKhau.class).getResultList();
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanKhaus;
    }

    public void deleteNhanKhau(int id) {
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM NhanKhau "  + "WHERE id = :id").setParameter("id", id);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
