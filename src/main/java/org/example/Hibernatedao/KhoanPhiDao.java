package org.example.Hibernatedao;

import javafx.scene.control.Alert;
import org.example.EntityAll.KhoanPhi;
import org.example.Function.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class KhoanPhiDao implements Save<KhoanPhi>, SelectAll, Update<KhoanPhi> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session = null;
    public static KhoanPhiDao getInstance() {return new KhoanPhiDao();}

    public KhoanPhiDao() {
        super();
    }


    public void delete(KhoanPhi khoanPhi) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.delete(khoanPhi);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(KhoanPhi khoanPhi) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.save(khoanPhi);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            System.out.println("Luu khoan phi co loi");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<KhoanPhi> selectAll() {
        List<KhoanPhi> khoanPhis;
        try {

            session = Hibernate.getSession(sessionFactory);
            khoanPhis = session.createQuery("FROM KhoanPhi", KhoanPhi.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Không tìm thấy phí");
            alert1.showAndWait();
            throw new RuntimeException(e);
        }
        return khoanPhis;
    }

    @Override
    public void update(KhoanPhi khoanPhi) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.update(khoanPhi);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            System.out.println("Luu ho khau co loi");
            throw new RuntimeException(e);
        }
    }


    public KhoanPhi selectByName(String name) {
        KhoanPhi khoanPhis;
        try {
            session=Hibernate.getSession(sessionFactory);
            khoanPhis = session
                    .createQuery("FROM KhoanPhi d WHERE d.tenKhoanPhi = :name", KhoanPhi.class)
                    .setParameter("name", name )
                    .uniqueResult();
            Hibernate.closeSession(session);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
        return khoanPhis;
    }
    public KhoanPhi selectByid(int name) {
        KhoanPhi khoanPhis;
        try {
            session=Hibernate.getSession(sessionFactory);
            khoanPhis = session
                    .createQuery("FROM KhoanPhi d WHERE d.id = :name", KhoanPhi.class)
                    .setParameter("name", name )
                    .uniqueResult();
            Hibernate.closeSession(session);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
        return khoanPhis;
    }
}
