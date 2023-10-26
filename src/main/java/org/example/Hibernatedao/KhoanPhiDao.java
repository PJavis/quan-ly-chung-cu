package org.example.Hibernatedao;

import org.example.EntityAll.NopPhi;
import org.example.Function.Save;
import org.example.Function.SelectAll;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class KhoanPhiDao implements Save<NopPhi>, SelectAll {
    private SessionFactory sessionFactory;
    private Session session;

    public static KhoanPhiDao getInstance() {
        return new KhoanPhiDao();
    }

    @Override
    public boolean save(NopPhi nopPhi) {
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            Serializable serializable = (Serializable) session.save(nopPhi);
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
        List<NopPhi> nopPhis;
        try {
            sessionFactory = Hibernate.getSessionFactory();
            session = Hibernate.getSession(sessionFactory);
            nopPhis = session.createQuery("FROM NopPhi", NopPhi.class).getResultList();
            Hibernate.closeSession(session);
            Hibernate.closeSessionFactory(sessionFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nopPhis;
    }
}
