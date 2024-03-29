package org.example.Model.Hibernatedao;

import org.example.Model.EntityAll.HoKhau;
import org.example.Model.EntityAll.PhuongTien;
import org.example.Model.Function.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PhuongTienDao implements Save<PhuongTien>, SelectByName<PhuongTien>, SelectAll, Update<PhuongTien>{
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

    public void delete(PhuongTien phuongTien) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.delete(phuongTien);
            Hibernate.closeSession(session);

        } catch (Exception e) {
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


    public List<PhuongTien> selectByHoKhau(HoKhau hoKhau) {
        List<PhuongTien> phuongTiens;
        try {
            session = Hibernate.getSession(sessionFactory);
            phuongTiens = session.createQuery("FROM PhuongTien  WHERE hoKhau= :hoKhau", PhuongTien.class)
                    .setParameter("hoKhau", hoKhau)
                    .getResultList();
            Hibernate.closeSession(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return phuongTiens;
    }

    @Override
    public List<PhuongTien> selectByName(String name) {
        List<PhuongTien> phuongTiens;
        try {
            session = Hibernate.getSession(sessionFactory);
            phuongTiens = session.createQuery("FROM PhuongTien WHERE bienSoXe LIKE :name", PhuongTien.class)
                    .setParameter("name", "%" + name + "%")
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
