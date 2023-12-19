package org.example.Hibernatedao;

import org.example.EntityAll.HoKhau;
import org.example.Function.Save;
import org.example.Function.SelectAll;
import org.example.Function.Update;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HoKhauDao implements Save<HoKhau>, SelectAll, Update<HoKhau>{
 private SessionFactory sessionFactory = Hibernate.getSessionFactory();
 private  Session session = null;

    public static HoKhauDao getInstance() {
        return new HoKhauDao();
    }

    public void save(HoKhau hoKhau){
       try {
           session=Hibernate.getSession(sessionFactory);
           session.save(hoKhau);
           Hibernate.closeSession(session);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    public List<HoKhau> selectAll() {
        List<HoKhau> hoKhaus;
        try {

            session = Hibernate.getSession(sessionFactory);
            hoKhaus = session.createQuery("FROM HoKhau", HoKhau.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return hoKhaus;
    }
    public void delete(HoKhau hoKhau){
        try {
            session=Hibernate.getSession(sessionFactory);
            session.delete(hoKhau);
            Hibernate.closeSession(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    public HoKhau selectById(int sophong, int sotang) {
        HoKhau hoKhau;
        try {
            session = Hibernate.getSession(sessionFactory);
            hoKhau = session.createQuery("FROM HoKhau WHERE id = :sophong AND soTang = :sotang", HoKhau.class)
                    .setParameter("sophong", sophong)
                    .setParameter("sotang", sotang)
                    .uniqueResult();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return hoKhau;
    }


    @Override
    public void update(HoKhau hoKhau) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.update(hoKhau);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            System.out.println("Luu ho khau co loi");
            throw new RuntimeException(e);
        }
    }


}
