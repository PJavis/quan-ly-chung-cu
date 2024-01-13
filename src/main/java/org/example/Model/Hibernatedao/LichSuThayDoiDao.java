package org.example.Model.Hibernatedao;

import org.example.Model.EntityAll.HoKhau;
import org.example.Model.EntityAll.LichSuThayDoi;
import org.example.Model.EntityAll.PhuongTien;
import org.example.Model.Function.Save;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class LichSuThayDoiDao implements Save<LichSuThayDoi> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session;
    public static LichSuThayDoiDao getInstance() {return new LichSuThayDoiDao(); }

    @Override
    public void save(LichSuThayDoi lichSuThayDoi) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.save(lichSuThayDoi);
            Hibernate.closeSession(session);
        } catch (Exception e) {
            System.out.println("Luu lich su thay doi co loi");
            throw new RuntimeException(e);
        }
    }
    public void delete(LichSuThayDoi phuongTien) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.delete(phuongTien);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<LichSuThayDoi> selectByHoKhau(HoKhau hoKhau) {
        List<LichSuThayDoi> phuongTiens;
        try {
            session = Hibernate.getSession(sessionFactory);
            phuongTiens = session.createQuery("FROM LichSuThayDoi  WHERE hoKhau= :hoKhau", LichSuThayDoi.class)
                    .setParameter("hoKhau", hoKhau)
                    .getResultList();
            Hibernate.closeSession(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return phuongTiens;
    }
}
