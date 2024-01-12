package org.example.Model.Hibernatedao;

import org.example.Model.EntityAll.LichSuGiaoDichPhiGuiXe;

import org.example.Model.EntityAll.PhuongTien;
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
    public void delete(LichSuGiaoDichPhiGuiXe phuongTien) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.delete(phuongTien);
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
