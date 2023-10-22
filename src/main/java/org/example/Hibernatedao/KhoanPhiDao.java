package org.example.Hibernatedao;

import org.example.EntityAll.KhoanPhi;
import org.example.Function.Save;
import org.example.Function.SelectAll;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KhoanPhiDao implements Save<KhoanPhi>, SelectAll {
    private SessionFactory sessionFactory;
    private Session session;

    public static KhoanPhiDao getInstance() {
        return new KhoanPhiDao();
    }

    ;

    @Override
    public boolean save(KhoanPhi khoanPhi) {
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            Serializable serializable = (Serializable) session.save(khoanPhi);
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
            return (serializable != null);
        } catch (Exception e) {
            System.out.println("Luu khoan phi co loi");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<?> selectAll() {
        List<KhoanPhi> khoanPhis = new ArrayList<>();
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            khoanPhis = session.createQuery("FROM KhoanPhi", KhoanPhi.class).getResultList();
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return khoanPhis;
    }
}
