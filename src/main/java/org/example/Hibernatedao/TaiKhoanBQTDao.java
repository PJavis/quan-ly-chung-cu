package org.example.Hibernatedao;

import org.example.EntityAll.TaiKhoanBQT;
import org.example.Function.Delete;
import org.example.Function.Save;
import org.example.Function.SelectAll;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class TaiKhoanBQTDao implements Save<TaiKhoanBQT>, Delete, SelectAll {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session;
    public static TaiKhoanBQTDao getInstance() {return new TaiKhoanBQTDao(); }

    @Override
    public boolean save(TaiKhoanBQT taiKhoanBQT) {
        try {

            session = Hibernate.getSession(sessionFactory);
            Serializable serializable = (Serializable) session.save(taiKhoanBQT);
            Hibernate.closeSession(session);

            return (serializable!=null);
        } catch (Exception e) {
            System.out.println("Luu tai khoan ban quan tri co loi");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<?> selectAll() {
        List<TaiKhoanBQT> taiKhoanBQTS;
        try {

            session=Hibernate.getSession(sessionFactory);
            taiKhoanBQTS = session.createQuery("FROM TaiKhoanBQT", TaiKhoanBQT.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return taiKhoanBQTS;
    }

    @Override
    public void delete(int id) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM TaiKhoanBQT "  + "WHERE id = :id").setParameter("id", id).executeUpdate();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
