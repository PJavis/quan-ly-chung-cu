package org.example.Hibernatedao;

import org.example.EntityAll.HoKhau;
import org.example.EntityAll.NhanKhau;
import org.example.Function.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NhanKhauDao implements Save<NhanKhau>, SelectAll, SelectByName<NhanKhau>, Update<NhanKhau>, StatisticsTime<NhanKhau> {
    private SessionFactory sessionFactory = Hibernate.getSessionFactory();
    private Session session;
    public static NhanKhauDao getInstance() {return new NhanKhauDao(); }

    @Override
    public void save(NhanKhau nhanKhau) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.save(nhanKhau);
            Hibernate.closeSession(session);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NhanKhau> selectAll() {
        List<NhanKhau> nhanKhaus;
        try {

            session=Hibernate.getSession(sessionFactory);
            nhanKhaus = session.createQuery("FROM NhanKhau", NhanKhau.class).getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanKhaus;
    }
     //cai nay de thong ke theo nam sinh
    public Map<Integer, Long> tinhnamsinh() {
        Map<Integer, Long> yearDistribution = new HashMap<>();

        try {
            session = Hibernate.getSession(sessionFactory);
            List<NhanKhau> nhanKhaus = session.createQuery("FROM NhanKhau", NhanKhau.class).getResultList();
            Hibernate.closeSession(session);

            for (NhanKhau temp : nhanKhaus) {
                LocalDate createDate = temp.getNgaySinh().toLocalDate();
                if (createDate != null) {
                    int year = createDate.getYear();
                    yearDistribution.put(year, yearDistribution.getOrDefault(year, 0L) + 1);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return yearDistribution;
    }
    public Map<String, Long> tinhtilenamnu() {
        Map<String, Long> genderDistribution = new HashMap<>();

        try {
            session = Hibernate.getSession(sessionFactory);
            List<NhanKhau> nhanKhaus = session.createQuery("FROM NhanKhau", NhanKhau.class).getResultList();
            Hibernate.closeSession(session);

            for (NhanKhau temp : nhanKhaus) {
                String gender = temp.getGioiTinh() == 1 ? "Nam" : "Nữ";
                genderDistribution.put(gender, genderDistribution.getOrDefault(gender, 0L) + 1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return genderDistribution;
    }
    @Override
    public Map<Integer, Long> calculateTimeDistribution(List<NhanKhau> in) {
        Map<Integer, Long> ageDistribution = new HashMap<>();

        for (NhanKhau temp : in) {
            int age = Period.between(temp.getNgaySinh().toLocalDate(), LocalDate.now()).getYears();
            ageDistribution.put(age, ageDistribution.getOrDefault(age, 0L) + 1);
        }

        return ageDistribution;
    }
    public void delete(NhanKhau nhanKhau) {
        try {

            session = Hibernate.getSession(sessionFactory);
            session.delete(nhanKhau);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NhanKhau> selectByName(String name) {
        List<NhanKhau> nhanKhaus;
        try {

            session=Hibernate.getSession(sessionFactory);
            nhanKhaus = session.createQuery("FROM NhanKhau n WHERE n.ten LIKE :name", NhanKhau.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanKhaus;
    }

    @Override
    public void update(NhanKhau nhanKhau) {
        try {

            session=Hibernate.getSession(sessionFactory);
            session.update(nhanKhau);
            Hibernate.closeSession(session);

        } catch (Exception e) {
            System.out.println("Luu nhan khau co loi");
            throw new RuntimeException(e);
        }
    }



    public List<NhanKhau> selectNhanKhauById(HoKhau hoKhau) {
        List<NhanKhau> nhanKhau;
        try {

            session = Hibernate.getSession(sessionFactory);
            nhanKhau = session.createQuery("FROM NhanKhau  WHERE  hoKhau= :hoKhau  ", NhanKhau.class)
                    .setParameter("hoKhau", hoKhau)

                    .getResultList();
            Hibernate.closeSession(session);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nhanKhau;
    }


}
