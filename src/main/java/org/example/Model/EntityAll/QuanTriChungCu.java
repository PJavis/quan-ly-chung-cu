package org.example.Model.EntityAll;


import jakarta.persistence.*;

@Entity
@Table(name = "quan_tri_chung_cu")
public class QuanTriChungCu {
    @Id
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



    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    @Lob
    @Column(name = "avatar")
    private String imagelink;

    public QuanTriChungCu() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed


    public QuanTriChungCu(String tenNguoiQuanTri, String email, String soDienThoai, String diaChi, String moTa,String imagelink) {
        this.id = id;
        this.tenNguoiQuanTri = tenNguoiQuanTri;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.imagelink = imagelink;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    public void setId(int id){
        this.id = id;
    }
    public String laylinhanh() {
        return imagelink;
    }

    public void setImagelink(String imagelink1){
        this.imagelink = imagelink1;
    }

}
