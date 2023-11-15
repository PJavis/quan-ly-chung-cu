package org.example.Hibernatedao;

import org.example.EntityAll.DanhSachKhoanPhi;
import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NopPhi;
import org.example.Function.Save;
import org.example.Function.SelectAll;
import org.example.Function.Update;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class NopPhiDao implements Save<NopPhi>, SelectAll, Update<NopPhi> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session = null;
    public static NopPhiDao getInstance() {return  new NopPhiDao();}

    @Override
    public boolean save(NopPhi nopPhi) {
        try {

            session=Hibernate.getSession(sessionFactory);
            Serializable serializable = (Serializable) session.save(nopPhi);
            Hibernate.closeSession(session);

            return (serializable!=null);
        } catch (Exception e) {
            System.out.println("Luu nop phi co loi");
            throw new RuntimeException(e);
        }
    }

    public List<NopPhi> selectAll() {
        List<NopPhi> nopPhis;
        try {

            session = Hibernate.getSession(sessionFactory);
            nopPhis = session.createQuery("FROM NopPhi", NopPhi.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nopPhis;
    }

    public NopPhi selectByTenKhoanPhiVaHoKhau(String tenKhoanPhi, HoKhau hoKhau) {
        DanhSachKhoanPhi danhSachKhoanPhi = DanhSachKhoanPhiDao.getInstance().selectByName(tenKhoanPhi).get(0);
        NopPhi nopPhi;
        try {
            session=Hibernate.getSession(sessionFactory);
            nopPhi = session.createQuery("FROM NopPhi n WHERE n.idKhoanPhi = :id AND n.hoKhau = :hoKhau", NopPhi.class)
                    .setParameter("id", danhSachKhoanPhi.getId())
                    .setParameter("hoKhau", hoKhau.getId())
                    .uniqueResult();
            Hibernate.closeSession(session);
        } catch (Exception e) {
            System.out.println("Tim nop phi co loi");
            throw new RuntimeException(e);
        }

        return nopPhi;
    }

    @Override
    public void update(NopPhi in) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.update(in);
            Hibernate.closeSession(session);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
