package org.example.Hibernatedao;

import org.example.EntityAll.LichSuGiaoDich;
import org.example.Function.Save;
import org.example.Function.SelectAll;
import org.example.Function.Update;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
}
