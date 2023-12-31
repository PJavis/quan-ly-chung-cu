package org.example.EntityAll;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "quan_tri_chung_cu")
public class QuanTriChungCu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_nguoi_quan_tri", nullable = false, length = 255)
    private String tenNguoiQuanTri;

    @Column(name = "email")
    private String email;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "ngay_bat_dau_quan_tri")
    private Date ngayBatDauQuanTri;

    @Column(name = "ngay_ket_thuc_quan_tri")
    private Date ngayKetThucQuanTri;

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    public QuanTriChungCu() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed


    public QuanTriChungCu(String tenNguoiQuanTri, String email, String soDienThoai, String diaChi, Date ngayBatDauQuanTri, Date ngayKetThucQuanTri, String moTa) {
        this.tenNguoiQuanTri = tenNguoiQuanTri;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.ngayBatDauQuanTri = ngayBatDauQuanTri;
        this.ngayKetThucQuanTri = ngayKetThucQuanTri;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public String getTenNguoiQuanTri() {
        return tenNguoiQuanTri;
    }

    public void setTenNguoiQuanTri(String tenNguoiQuanTri) {
        this.tenNguoiQuanTri = tenNguoiQuanTri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayBatDauQuanTri() {
        return ngayBatDauQuanTri;
    }

    public void setNgayBatDauQuanTri(Date ngayBatDauQuanTri) {
        this.ngayBatDauQuanTri = ngayBatDauQuanTri;
    }

    public Date getNgayKetThucQuanTri() {
        return ngayKetThucQuanTri;
    }

    public void setNgayKetThucQuanTri(Date ngayKetThucQuanTri) {
        this.ngayKetThucQuanTri = ngayKetThucQuanTri;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
