package org.example.EntityAll;

import jakarta.persistence.*;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

@Entity
@Table(name = "danh_sach_khoan_phi")
public class KhoanPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_khoan_phi")
    private String tenKhoanPhi;

    @Column(name = "loai_khoan_phi")
    private String loaiKhoanPhi;


    @Column(name = "bat_dau")
    private Date batDau;

    @Column(name = "ket_thuc")
    private Date ketThuc;

    @Column(name = "gia_tri")
    private double giaTri;

    @Column(name="tong_so_tien")
    private double tongsotien;
    @Column(name="don_vi")
    private String donVi;
    @OneToMany(mappedBy = "khoanPhi", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NopPhi> nopPhis ;
    public KhoanPhi(String tenKhoanPhi, String loaiKhoanPhi, Date batDau, Date ketThuc, double giaTri, double tongsotien,Set<NopPhi> nopPhis) {
        this.tenKhoanPhi = tenKhoanPhi;
        this.loaiKhoanPhi = loaiKhoanPhi;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.giaTri = giaTri;
        this.tongsotien=tongsotien;
        this.nopPhis=nopPhis;
    }

    public KhoanPhi() {

    }

    public Set<NopPhi> getNopPhis() {
        return nopPhis;
    }

    public void setNopPhis(Set<NopPhi> nopPhis) {
        this.nopPhis = nopPhis;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public double getTongsotien() {
        return tongsotien;
    }

    public void setTongsotien(double tongsotien) {
        this.tongsotien = tongsotien;
    }

    public int getId() {
        return id;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
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

    public Date getBatDau() {
        return batDau;
    }

    public void setBatDau(Date batDau) {
        this.batDau = batDau;
    }

    public Date getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(Date ketThuc) {
        this.ketThuc = ketThuc;
    }
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(ketThuc);
    }
    public String getFormattedDatebatdau(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(batDau);
    }
    public  String getDecimalFormatsotien(){
        String pattern = "#,##0" + (getGiaTri() % 1 == 0 ? "" : ".#########");
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(giaTri);
    }
    public  String getDecimalFormatsotiendanop(){
        String pattern = "#,##0" + (getTongsotien() % 1 == 0 ? "" : ".#########");
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(tongsotien);
    }
}
