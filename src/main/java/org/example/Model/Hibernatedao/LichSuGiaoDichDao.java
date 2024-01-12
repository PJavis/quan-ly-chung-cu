package org.example.Model.Hibernatedao;

import org.example.Model.EntityAll.LichSuGiaoDich;
import org.example.Model.EntityAll.NopPhi;
import org.example.Model.Function.Save;
import org.example.Model.Function.SelectAll;
import org.example.Model.Function.Update;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;

public class LichSuGiaoDichDao implements SelectAll, Save<LichSuGiaoDich>, Update<LichSuGiaoDich> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session = null;

    public static LichSuGiaoDichDao getInstance() { return new LichSuGiaoDichDao();};

    @Override
    public void save(LichSuGiaoDich lichSuGiaoDich){
        try {
            session=Hibernate.getSession(sessionFactory);
            session.save(lichSuGiaoDich);
            Hibernate.closeSession(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LichSuGiaoDich> selectAll() {
        List<LichSuGiaoDich> lichSuGiaoDiches;
        try {

            session = Hibernate.getSession(sessionFactory);
            lichSuGiaoDiches = session.createQuery("FROM LichSuGiaoDich ", LichSuGiaoDich.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lichSuGiaoDiches;
    }

    @Override
    public void update(LichSuGiaoDich lichSuGiaoDich) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.update(lichSuGiaoDich);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            System.out.println("Luu lich su giao dich co loi");
            throw new RuntimeException(e);
        }
    }
    public List<LichSuGiaoDich> selectByCondition(NopPhi nopPhi) {
        List<LichSuGiaoDich> lichSuGiaoDich;
        try {
            session = Hibernate.getSession(sessionFactory);
            lichSuGiaoDich = session.createQuery("FROM LichSuGiaoDich  WHERE  nopPhi= :nopPhi", LichSuGiaoDich.class)
                    .setParameter("nopPhi", nopPhi)
                    .getResultList();
            Hibernate.closeSession(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lichSuGiaoDich;
    }

    public List<LichSuGiaoDich> selectInTime(Date t1, Date t2, int id) {
        List<LichSuGiaoDich> lichSuGiaoDiches;
        try {
            session = Hibernate.getSession(sessionFactory);
            lichSuGiaoDiches = session.createQuery("FROM LichSuGiaoDich l WHERE l.nopPhi.khoanPhi.id= :id AND thoigiangiaodich BETWEEN :t1 AND :t2", LichSuGiaoDich.class)
                    .setParameter("t1", t1).setParameter("t2", t2).setParameter("id", id)
                    .getResultList();
            Hibernate.closeSession(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lichSuGiaoDiches;
    }
}
