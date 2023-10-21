package org.example.Hibernate.dao;

import org.example.EntityAll.LichSuThayDoi;
import org.example.EntityAll.TaiKhoanBQT;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public class TaiKhoanBQTDao {
    private SessionFactory sessionFactory;
    private Session session;
    public static TaiKhoanBQTDao getInstance() {return new TaiKhoanBQTDao(); };

    public boolean save(TaiKhoanBQT taiKhoanBQT){
        try {
            sessionFactory=Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            Serializable serializable= (Serializable) session.save(taiKhoanBQT);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
            return (serializable!=null);
        } catch (Exception e) {
            System.out.println("Luu tai khoan ban quan tri co loi");
            throw new RuntimeException(e);
        }
    }
}
