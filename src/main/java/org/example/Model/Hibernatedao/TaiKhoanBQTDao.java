package org.example.Model.Hibernatedao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.Model.EntityAll.QuanTriChungCu;
import org.example.Model.EntityAll.TaiKhoanBQT;
import org.example.Model.Function.Delete;
import org.example.Model.Function.Save;
import org.example.Model.Function.SelectAll;
import org.example.Model.Function.Update;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class TaiKhoanBQTDao implements Save<TaiKhoanBQT>, Delete, SelectAll , Update<TaiKhoanBQT> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session=null;
    public static TaiKhoanBQTDao getInstance() {return new TaiKhoanBQTDao(); }

    @Override
    public void save(TaiKhoanBQT taiKhoanBQT) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.save(taiKhoanBQT);
            Hibernate.closeSession(session);
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
    public TaiKhoanBQT laythongtin(String taiKhoan, String matKhau) {
        try {
            session = Hibernate.getSession(sessionFactory);
            String jpql = "SELECT tk FROM TaiKhoanBQT tk WHERE tk.taiKhoan = :taiKhoan AND tk.matKhau = :matKhau";
            TypedQuery<TaiKhoanBQT> query = session.createQuery(jpql, TaiKhoanBQT.class);
            query.setParameter("taiKhoan", taiKhoan);
            query.setParameter("matKhau", matKhau);
            TaiKhoanBQT taiKhoanBQT = query.getSingleResult();
            Hibernate.closeSession(session);
            return taiKhoanBQT;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public QuanTriChungCu layThongTinQuanTriChungCu(int idTaiKhoanBQT) {
        try {
            session = Hibernate.getSession(sessionFactory);
            String jpql = "SELECT qtcc FROM QuanTriChungCu qtcc WHERE qtcc.id = :idTaiKhoanBQT";
            TypedQuery<QuanTriChungCu> query = session.createQuery(jpql, QuanTriChungCu.class);
            query.setParameter("idTaiKhoanBQT", idTaiKhoanBQT);
            QuanTriChungCu quanTriChungCu = query.getSingleResult();
            Hibernate.closeSession(session);
            return quanTriChungCu;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public TaiKhoanBQT kiemTraDangNhap(String taiKhoan, String matKhau) {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            String hql = "FROM TaiKhoanBQT WHERE taiKhoan = :taiKhoan AND matKhau = :matKhau";
            TaiKhoanBQT result = (TaiKhoanBQT) session.createQuery(hql)
                    .setParameter("taiKhoan", taiKhoan)
                    .setParameter("matKhau", matKhau)
                    .uniqueResult();

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public void update(TaiKhoanBQT taiKhoanBQT) {
        try {
            session = Hibernate.getSession(sessionFactory);
            taiKhoanBQT = (TaiKhoanBQT) session.merge(taiKhoanBQT);
            Hibernate.closeSession(session);
        } catch (Exception e) {
            System.out.println("Lưu thông tin tài khoản có lỗi");
            throw new RuntimeException(e);
        }
    }
}
