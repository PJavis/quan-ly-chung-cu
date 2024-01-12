package org.example.Model.EntityAll;

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

    @Column(name = "thay_doi", length = 255)
    private String thayDoi;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "so_phong", referencedColumnName = "id"),
            @JoinColumn(name = "so_tang", referencedColumnName = "so_tang")
    })
    private HoKhau hoKhau;

    public LichSuThayDoi() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed

    public LichSuThayDoi(int id, Date ngayThayDoi, String thayDoi, HoKhau hoKhau) {
        this.id = id;
        this.ngayThayDoi = ngayThayDoi;
        this.thayDoi = thayDoi;
        this.hoKhau = hoKhau;
    }

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
