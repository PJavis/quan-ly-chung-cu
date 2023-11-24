package org.example.Hibernatedao;

import javafx.scene.control.Alert;
import org.example.EntityAll.DanhSachKhoanPhi;
import org.example.Function.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class DanhSachKhoanPhiDao implements Save<DanhSachKhoanPhi>, Delete, SelectAll, Update<DanhSachKhoanPhi>, SelectByName<DanhSachKhoanPhi> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session = null;
    public static DanhSachKhoanPhiDao getInstance() {return new DanhSachKhoanPhiDao();}

    public DanhSachKhoanPhiDao() {
        super();
    }

    @Override
    public void delete(int id) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM DanhSachKhoanPhi WHERE id = :id",DanhSachKhoanPhi.class).setParameter("id", id).executeUpdate();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(DanhSachKhoanPhi danhSachKhoanPhi) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.save(danhSachKhoanPhi);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            System.out.println("Luu khoan phi co loi");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DanhSachKhoanPhi> selectAll() {
        List<DanhSachKhoanPhi> danhSachKhoanPhis;
        try {

            session = Hibernate.getSession(sessionFactory);
            danhSachKhoanPhis = session.createQuery("FROM DanhSachKhoanPhi", DanhSachKhoanPhi.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Không tìm thấy phí");
            alert1.showAndWait();
            throw new RuntimeException(e);
        }
        return danhSachKhoanPhis;
    }

    @Override
    public void update(DanhSachKhoanPhi danhSachKhoanPhi) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.update(danhSachKhoanPhi);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            System.out.println("Luu ho khau co loi");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DanhSachKhoanPhi> selectByName(String name) {
        List<DanhSachKhoanPhi> danhSachKhoanPhis;
        try {
            session=Hibernate.getSession(sessionFactory);
            danhSachKhoanPhis = session
                    .createQuery("FROM DanhSachKhoanPhi d WHERE d.tenKhoanPhi = :name", DanhSachKhoanPhi.class)
                    .setParameter("name", name )
                    .getResultList();
            Hibernate.closeSession(session);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
        return danhSachKhoanPhis;
    }
}
