package org.example.EntityAll;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "khoan_phi")
public class KhoanPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_khoan_phi", nullable = false, length = 255)
    private String tenKhoanPhi;

    @Column(name = "loai_khoan_phi", nullable = false, length = 50)
    private String loaiKhoanPhi;

    @Column(name = "gia_tri", nullable = false)
    private double giaTri;

    @Column(name = "ngay_bat_dau", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayKetThuc;

    @ManyToOne
    @JoinColumn(name = "ho_khau_id", referencedColumnName = "id")
    private HoKhau hoKhau;

    public KhoanPhi() {
        // Default constructor required by Hibernate
    }



    public KhoanPhi(int id, String tenKhoanPhi, String loaiKhoanPhi, double giaTri, Date ngayBatDau, Date ngayKetThuc, HoKhau hoKhau) {
        this.id = id;
        this.tenKhoanPhi = tenKhoanPhi;
        this.loaiKhoanPhi = loaiKhoanPhi;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hoKhau = hoKhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKhoanPhi() {
        return tenKhoanPhi;
    }

    public void setTenKhoanPhi(String tenKhoanPhi) {
        this.tenKhoanPhi = tenKhoanPhi;
    }

    public String getLoaiKhoanPhi() {
        return loaiKhoanPhi;
    }

    public void setLoaiKhoanPhi(String loaiKhoanPhi) {
        this.loaiKhoanPhi = loaiKhoanPhi;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public HoKhau getHoKhau() {
        return hoKhau;
    }

    public void setHoKhau(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
    }
}
