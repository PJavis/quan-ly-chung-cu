package org.example.Hibernatedao;

import javafx.scene.control.Alert;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NhanKhau;
import org.example.Function.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HoKhauDao implements Save<HoKhau>, Delete, SelectAll, Update<HoKhau>{
 private SessionFactory sessionFactory = Hibernate.getSessionFactory();
 private  Session session = null;

    public static HoKhauDao getInstance() {
        return new HoKhauDao();
    }

    public boolean save(HoKhau hoKhau){
       try {
           session=Hibernate.getSession(sessionFactory);
           Serializable serializable = (Serializable) session.save(hoKhau);
           Hibernate.closeSession(session);

           return (serializable!=null);
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

    public void delete(int id) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM HoKhau WHERE id = :id" ).setParameter("id", id).executeUpdate();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public HoKhau selectById(int id) {
        HoKhau hoKhau;
        try {

            session = Hibernate.getSession(sessionFactory);
            hoKhau = session.createQuery("FROM HoKhau WHERE id = :id", HoKhau.class).setParameter("id", id).uniqueResult();
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

    public NhanKhau layChuHo(HoKhau hoKhau) {
        List<NhanKhau> nhanKhau= NhanKhauDao.getInstance().selectNhanKhauById(hoKhau.getId());
        NhanKhau out = null;
        for (NhanKhau n : nhanKhau) {
            if (n.isChuHo()) {
                out = n;
                break;
            }
        }
        return out;
    }

    public List<NhanKhau> layNhanKhau(HoKhau hoKhau) {
        List<NhanKhau> nhanKhau= NhanKhauDao.getInstance().selectNhanKhauById(hoKhau.getId());
        List<NhanKhau> out = new ArrayList<>();
        for (NhanKhau n : nhanKhau) {
            if (!n.isChuHo()) {
                out.add(n);
            }
        }
        return out;
    }
}
