package org.example.Hibernate.dao;

import org.example.EntityAll.LichSuThayDoi;
import org.example.EntityAll.NhanKhau;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

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
}
