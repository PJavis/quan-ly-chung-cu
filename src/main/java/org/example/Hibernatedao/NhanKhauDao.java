package org.example.Hibernatedao;

import javafx.scene.control.Alert;
import org.example.EntityAll.NhanKhau;
import org.example.Function.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class NhanKhauDao implements Save<NhanKhau>, SelectAll, Delete, SelectByName<NhanKhau>, Update<NhanKhau>,SelectById<NhanKhau> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session;
    public static NhanKhauDao getInstance() {return new NhanKhauDao(); }

    @Override
    public boolean save(NhanKhau nhanKhau) {
        try {

            session=Hibernate.getSession(sessionFactory);
            Serializable serializable= (Serializable) session.save(nhanKhau);
            Hibernate.closeSession(session);

            return (serializable!=null);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NhanKhau> selectAll() {
        List<NhanKhau> nhanKhaus;
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            nhanKhaus = session.createQuery("FROM nhanKhau", NhanKhau.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanKhaus;
    }

    @Override
    public void delete(int id) {
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM NhanKhau "  + "WHERE id = :id").setParameter("id", id).executeUpdate();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NhanKhau> selectByName(String name) {
        List<NhanKhau> nhanKhaus;
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            nhanKhaus = session.createQuery("FROM nhanKhau WHERE ten LIKE %name%:name").setParameter("name", name).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanKhaus;
    }

    @Override
    public void update(NhanKhau nhanKhau) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.update(nhanKhau);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            System.out.println("Luu ho khau co loi");
            throw new RuntimeException(e);
        }
    }

    @Override
    public NhanKhau selectById(int id) {
        NhanKhau nhanKhau;
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            nhanKhau = session.createQuery("FROM NhanKhau WHERE id = :id", NhanKhau.class).setParameter("id", id).uniqueResult();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanKhau;

    }

    public List<NhanKhau> selectNhanKhauById(int id) {
        List<NhanKhau> nhanKhau = null;
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            nhanKhau = session.createQuery("FROM NhanKhau n WHERE n.hoKhau.id = :id ", NhanKhau.class)
                    .setParameter("id", id)
                    .getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanKhau;
    }


}
