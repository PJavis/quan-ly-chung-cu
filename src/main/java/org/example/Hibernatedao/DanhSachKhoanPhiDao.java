package org.example.Hibernatedao;

import javafx.scene.control.Alert;
import org.example.EntityAll.KhoanPhi;
import org.example.Function.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class KhoanPhiDao implements Save<KhoanPhi>, Delete, SelectAll, Update<KhoanPhi>, SelectByName<KhoanPhi> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session = null;
    public static KhoanPhiDao getInstance() {return new KhoanPhiDao();}

    public KhoanPhiDao() {
        super();
    }

    @Override
    public void delete(int id) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM DanhSachKhoanPhi WHERE id = :id", KhoanPhi.class).setParameter("id", id).executeUpdate();
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
            khoanPhis = session.createQuery("FROM DanhSachKhoanPhi", KhoanPhi.class).getResultList();
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

    @Override
    public List<KhoanPhi> selectByName(String name) {
        List<KhoanPhi> khoanPhis;
        try {
            session=Hibernate.getSession(sessionFactory);
            khoanPhis = session
                    .createQuery("FROM DanhSachKhoanPhi d WHERE d.tenKhoanPhi = :name", KhoanPhi.class)
                    .setParameter("name", name )
                    .getResultList();
            Hibernate.closeSession(session);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
        return khoanPhis;
    }
}
