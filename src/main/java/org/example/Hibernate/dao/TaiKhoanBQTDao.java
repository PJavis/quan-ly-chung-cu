package org.example.Hibernate.dao;

import org.example.EntityAll.TaiKhoanBQT;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanBQTDao {
    private SessionFactory sessionFactory;
    private Session session;
    public static TaiKhoanBQTDao getInstance() {return new TaiKhoanBQTDao(); };

    public boolean save(TaiKhoanBQT taiKhoanBQT){
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            Serializable serializable = (Serializable) session.save(taiKhoanBQT);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
            return (serializable!=null);
        } catch (Exception e) {
            System.out.println("Luu tai khoan ban quan tri co loi");
            throw new RuntimeException(e);
        }
    }

    public List<TaiKhoanBQT> selectAll() {
        List<TaiKhoanBQT> taiKhoanBQTS = new ArrayList<>();
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            taiKhoanBQTS = session.createQuery("FROM TaiKhoanBQT", TaiKhoanBQT.class).getResultList();
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return taiKhoanBQTS;
    }

    public void deleteQuanTriChungCu(int id) {
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM TaiKhoanBQT "  + "WHERE id = :id").setParameter("id", id);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
