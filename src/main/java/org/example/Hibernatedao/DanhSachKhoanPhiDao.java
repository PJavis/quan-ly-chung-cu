package org.example.Hibernatedao;

import org.example.EntityAll.DanhSachKhoanPhi;
import org.example.Function.Delete;
import org.example.Function.Save;
import org.example.Function.SelectAll;
import org.example.Function.Update;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class DanhSachKhoanPhiDao implements Save<DanhSachKhoanPhi>, Delete, SelectAll, Update<DanhSachKhoanPhi> {
    private SessionFactory sessionFactory = null;
    private Session session = null;
    public static DanhSachKhoanPhiDao getInstance() {return new DanhSachKhoanPhiDao();}

    public DanhSachKhoanPhiDao() {
        super();
    }

    @Override
    public void delete(int id) {
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM DanhSachQuanLy WHERE id = :id" ).setParameter("id", id).executeUpdate();
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(DanhSachKhoanPhi danhSachKhoanPhi) {
        try {
            sessionFactory=Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            Serializable serializable = (Serializable) session.save(danhSachKhoanPhi);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
            return (serializable!=null);
        } catch (Exception e) {
            System.out.println("Luu khoan phi co loi");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DanhSachKhoanPhi> selectAll() {
        List<DanhSachKhoanPhi> danhSachKhoanPhis;
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            danhSachKhoanPhis = session.createQuery("FROM DanhSachQuanLy", DanhSachKhoanPhi.class).getResultList();
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return danhSachKhoanPhis;
    }

    @Override
    public void update(DanhSachKhoanPhi danhSachKhoanPhi) {
        try {
            sessionFactory=Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            session.update(danhSachKhoanPhi);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            System.out.println("Luu ho khau co loi");
            throw new RuntimeException(e);
        }
    }
}
