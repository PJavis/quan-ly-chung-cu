package org.example.Model.Hibernatedao;

import org.example.Model.EntityAll.LichSuThayDoi;
import org.example.Model.Function.Save;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
}
