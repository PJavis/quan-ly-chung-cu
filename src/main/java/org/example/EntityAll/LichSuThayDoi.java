package org.example.EntityAll;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lich_su_thay_doi")
public class LichSuThayDoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ngay_thay_doi", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayThayDoi;

    @Column(name = "thay_doi", length = 50)
    private String thayDoi;

    @ManyToOne
    @JoinColumn(name = "id_ho_khau", referencedColumnName = "id")
    private HoKhau hoKhau;

    public LichSuThayDoi() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayThayDoi() {
        return ngayThayDoi;
    }

    public void setNgayThayDoi(Date ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public String getThayDoi() {
        return thayDoi;
    }

    public void setThayDoi(String thayDoi) {
        this.thayDoi = thayDoi;
    }

    public HoKhau getHoKhau() {
        return hoKhau;
    }

    public void setHoKhau(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
    }
}
