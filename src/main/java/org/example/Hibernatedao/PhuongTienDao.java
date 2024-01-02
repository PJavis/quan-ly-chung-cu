package org.example.Hibernatedao;

import org.example.EntityAll.PhuongTien;
import org.example.Function.Save;
import org.example.Function.SelectAll;
import org.example.Function.SelectByHoKhau;
import org.example.Function.Update;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PhuongTienDao implements Save<PhuongTien>, SelectAll, Update<PhuongTien>, SelectByHoKhau<PhuongTien> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session = null;

    public static PhuongTienDao getInstance() {return new PhuongTienDao();}

    @Override
    public void save(PhuongTien in) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.save(in);
            Hibernate.closeSession(session);
        } catch (Exception e) {
            System.out.println("Luu phuong tien co loi");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PhuongTien> selectAll() {
        List<PhuongTien> phuongTiens ;
        try {
            session = Hibernate.getSession(sessionFactory);
            phuongTiens = session.createQuery("FROM PhuongTien", PhuongTien.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return phuongTiens;
    }

    @Override
    public List<PhuongTien> selectByHoKhau(int sotang, int sophong) {
        List<PhuongTien> phuongTiens;
        try {
            session = Hibernate.getSession(sessionFactory);
            phuongTiens = session.createQuery("FROM PhuongTien  WHERE soTang= :sotang AND soPhong= :sophong", PhuongTien.class)
                    .setParameter("sophong", sophong).setParameter("sotang", sotang)
                    .getResultList();
            Hibernate.closeSession(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return phuongTiens;
    }


    @Override
    public void update(PhuongTien in) {
        try {
            session=Hibernate.getSession(sessionFactory);
            session.update(in);
            Hibernate.closeSession(session);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
