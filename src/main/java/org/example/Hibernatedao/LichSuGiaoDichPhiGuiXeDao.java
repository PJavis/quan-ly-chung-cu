package org.example.Hibernatedao;

import org.example.EntityAll.LichSuGiaoDich;import org.example.EntityAll.LichSuGiaoDichPhiGuiXe;

import org.example.EntityAll.PhuongTien;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class LichSuGiaoDichPhiGuiXeDao {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session = null;

    public static LichSuGiaoDichPhiGuiXeDao getInstance() { return new LichSuGiaoDichPhiGuiXeDao();};

    public void save(LichSuGiaoDichPhiGuiXe lichSuGiaoDich){
        try {
            session=Hibernate.getSession(sessionFactory);
            session.save(lichSuGiaoDich);
            Hibernate.closeSession(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<LichSuGiaoDichPhiGuiXe> selectByCondition(PhuongTien phuongTien) {
        List<LichSuGiaoDichPhiGuiXe> lichSuGiaoDich;
        try {
            session = Hibernate.getSession(sessionFactory);
            lichSuGiaoDich = session.createQuery("FROM LichSuGiaoDichPhiGuiXe  WHERE  phuongTien= :phuongTien", LichSuGiaoDichPhiGuiXe.class)
                    .setParameter("phuongTien", phuongTien)
                    .getResultList();
            Hibernate.closeSession(session);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lichSuGiaoDich;
    }
}
