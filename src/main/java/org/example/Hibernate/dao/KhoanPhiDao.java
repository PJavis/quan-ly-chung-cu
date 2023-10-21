package org.example.Hibernate.dao;

import org.example.EntityAll.HoKhau;
import org.example.EntityAll.KhoanPhi;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public class KhoanPhiDao {
    private SessionFactory sessionFactory;
    private Session session;
    public static KhoanPhiDao getInstance() {return new KhoanPhiDao(); };

    public boolean save(KhoanPhi khoanPhi){
        try {
            sessionFactory=Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            Serializable serializable= (Serializable) session.save(khoanPhi);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
            return (serializable!=null);
        } catch (Exception e) {
            System.out.println("Luu khoan phi co loi");
            throw new RuntimeException(e);
        }
    }
}
