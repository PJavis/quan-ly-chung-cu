package org.example.Hibernate.dao;

import org.example.EntityAll.LichSuThayDoi;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public class LichSuThayDoiDao {
    private SessionFactory sessionFactory;
    private Session session;
    public static LichSuThayDoiDao getInstance() {return new LichSuThayDoiDao(); };

    public boolean save(LichSuThayDoi lichSuThayDoi){
        try {
            sessionFactory=Hibernate.getSessionFactory();
            session=Hibernate.getSession(sessionFactory);
            Serializable serializable= (Serializable) session.save(lichSuThayDoi);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
            return (serializable!=null);
        } catch (Exception e) {
            System.out.println("Luu lich su thay doi co loi");
            throw new RuntimeException(e);
        }
    }
}
