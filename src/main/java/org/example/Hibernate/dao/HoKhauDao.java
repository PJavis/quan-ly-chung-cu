package org.example.Hibernate.dao;

import org.example.EntityAll.HoKhau;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public class HoKhauDao {
 private SessionFactory SessionFactory = null;
 private  Session session = null;

    public static HoKhauDao getInstance() {
        return new HoKhauDao();
    }

    public boolean save(HoKhau hoKhau){
       try {
           SessionFactory=Hibernate.getSessionFactory();
           session=Hibernate.getSession(SessionFactory);
           Serializable serializable= (Serializable) session.save(hoKhau);
           Hibernate.closeSession(session);
           Hibernate.closeSessionFactory(SessionFactory);
           return (serializable!=null);
       } catch (Exception e) {
           System.out.println("Luu ho khau co loi");
           throw new RuntimeException(e);
       }
    }



}
