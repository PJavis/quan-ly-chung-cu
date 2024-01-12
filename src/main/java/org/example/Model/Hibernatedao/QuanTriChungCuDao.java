package org.example.Model.Hibernatedao;

import org.example.Model.EntityAll.QuanTriChungCu;
import org.example.Model.Function.Delete;
import org.example.Model.Function.Save;
import org.example.Model.Function.SelectAll;
import org.example.Model.Function.Update;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class QuanTriChungCuDao implements Save<QuanTriChungCu>, Delete, SelectAll, Update<QuanTriChungCu> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session = null;
    public static QuanTriChungCuDao getInstance() {return new QuanTriChungCuDao(); }
    @Override
    public void save(QuanTriChungCu quanTriChungCu) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.save(quanTriChungCu);
            Hibernate.closeSession(session);
        } catch (Exception e) {
            System.out.println("Luu quan tri chung cu co loi");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<?> selectAll() {
        List<QuanTriChungCu> quanTriChungCus;
        try {

            session=Hibernate.getSession(sessionFactory);
            quanTriChungCus = session.createQuery("FROM QuanTriChungCu", QuanTriChungCu.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return quanTriChungCus;
    }

    @Override
    public void delete(int id) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.createQuery("DELETE FROM QuanTriChungCu "  + "WHERE id = :id").setParameter("id", id).executeUpdate();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void update(QuanTriChungCu quanTriChungCu) {
        try {
            session=Hibernate.getSession(sessionFactory);
            quanTriChungCu = (QuanTriChungCu) session.merge(quanTriChungCu);
            Hibernate.closeSession(session);
        } catch (Exception e) {
            System.out.println("Lưu thông tin quản trị chung cư có lỗi");
            throw new RuntimeException(e);
        }
    }
}
