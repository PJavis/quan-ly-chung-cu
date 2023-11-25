package org.example.Hibernatedao;

import org.example.EntityAll.NhanKhau;
import org.example.Function.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class NhanKhauDao implements Save<NhanKhau>, SelectAll, SelectByName<NhanKhau>, Update<NhanKhau> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session;
    public static NhanKhauDao getInstance() {return new NhanKhauDao(); }

    @Override
    public void save(NhanKhau nhanKhau) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.save(nhanKhau);
            Hibernate.closeSession(session);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NhanKhau> selectAll() {
        List<NhanKhau> nhanKhaus;
        try {

            session=Hibernate.getSession(sessionFactory);
            nhanKhaus = session.createQuery("FROM NhanKhau", NhanKhau.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanKhaus;
    }


    public void delete(NhanKhau nhanKhau) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.delete(nhanKhau);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NhanKhau> selectByName(String name) {
        List<NhanKhau> nhanKhaus;
        try {

            session=Hibernate.getSession(sessionFactory);
            nhanKhaus = session.createQuery("FROM NhanKhau n WHERE n.ten LIKE :name", NhanKhau.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
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


    public NhanKhau selectById(int id) {
        NhanKhau nhanKhau;
        try {

            session = Hibernate.getSession(sessionFactory);
            nhanKhau = session.createQuery("FROM NhanKhau WHERE id = :id", NhanKhau.class).setParameter("id", id).uniqueResult();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanKhau;

    }

    public List<NhanKhau> selectNhanKhauById(int sophong,int sotang) {
        List<NhanKhau> nhanKhau = null;
        try {

            session = Hibernate.getSession(sessionFactory);
            nhanKhau = session.createQuery("FROM NhanKhau  WHERE  sophong= :sophong AND sotang = :sotang ", NhanKhau.class)
                    .setParameter("sophong", sophong)
                    .setParameter("sotang",sotang)
                    .getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanKhau;
    }


}
