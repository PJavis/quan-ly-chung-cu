package org.example.EntityAll;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "nop_phi")
public class NopPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_khoan_phi", nullable = false)
    private int idKhoanPhi;


    @Column(name = "ho_khau_id",nullable = false)
    private int hoKhau;

    @Column(name = "trang_thai_dong_phi")
    private boolean trangThaiDongPhi;

    @Column(name = "ngay_nop_phi")
    private Date ngayNopPhi;

    @Column(name = "so_tien_da_dong")
    private double soTienDaDong;


    public NopPhi() {
        // Default constructor required by Hibernate
    }

    public NopPhi(int id, int idKhoanPhi, int hoKhau, boolean trangThaiDongPhi, Date ngayNopPhi, double soTienDaDong) {
        this.id = id;
        this.idKhoanPhi = idKhoanPhi;
        this.hoKhau = hoKhau;
        this.trangThaiDongPhi = trangThaiDongPhi;
        this.ngayNopPhi = ngayNopPhi;
        this.soTienDaDong = soTienDaDong;
    }

    public int getId() {
        return id;
    }

    public void setIdKhoanPhi(int idKhoanPhi) {
        this.idKhoanPhi = idKhoanPhi;
    }

    public int getIdKhoanPhi() {
        return idKhoanPhi;
    }

    public int getHoKhau() {
        return hoKhau;
    }

    public void setHoKhau(int hoKhau) {
        this.hoKhau = hoKhau;
    }

    public Date getNgayNopPhi() {
        return ngayNopPhi;
    }

    public void setNgayNopPhi(Date ngayNopPhi) {
        this.ngayNopPhi = ngayNopPhi;
    }

    public boolean isTrangThaiDongPhi() {
        return trangThaiDongPhi;
    }

    public void setTrangThaiDongPhi(boolean trangThaiDongPhi) {
        this.trangThaiDongPhi = trangThaiDongPhi;
    }

    public double getSoTienDaDong() {
        return soTienDaDong;
    }

    public void setSoTienDaDong(double soTienDaDong) {
        this.soTienDaDong = soTienDaDong;
    }
}
