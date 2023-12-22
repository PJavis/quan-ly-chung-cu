package org.example.EntityAll;

import jakarta.persistence.*;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "lich_su_giao_dich")
public class LichSuGiaoDich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="ten_nguoi_nop")
    private String tennguoinop;

    @Column(name = "so_phong")
    private int sophong;
    @Column(name ="so_tang")
    private int sotang;

    @Column(name = "ten_khoan_phi")
    private String tenKhoanPhi;
    @Column(name = "id_khoan_phi", nullable = false)
    private int idKhoanPhi;

    @Column(name = "thoi_gian_giao_dich")
    private Date thoigiangiaodich;

    @Column(name = "gia_tri")
    private double giaTri;
    public  LichSuGiaoDich(){

    }

    public LichSuGiaoDich(String tennguoinop, int sophong, int sotang, String tenKhoanPhi, Date thoigiangiaodich, double giaTri) {
        this.tennguoinop = tennguoinop;
        this.sophong = sophong;
        this.sotang = sotang;
        this.tenKhoanPhi = tenKhoanPhi;
        this.thoigiangiaodich = thoigiangiaodich;
        this.giaTri = giaTri;
    }

    public int getIdKhoanPhi() {
        return idKhoanPhi;
    }

    public void setIdKhoanPhi(int idKhoanPhi) {
        this.idKhoanPhi = idKhoanPhi;
    }

    public String getTennguoinop() {
        return tennguoinop;
    }

    public void setTennguoinop(String tennguoinop) {
        this.tennguoinop = tennguoinop;
    }

    public int getSophong() {
        return sophong;
    }

    public void setSophong(int sophong) {
        this.sophong = sophong;
    }

    public int getSotang() {
        return sotang;
    }

    public void setSotang(int sotang) {
        this.sotang = sotang;
    }

    public String getTenKhoanPhi() {
        return tenKhoanPhi;
    }

    public void setTenKhoanPhi(String tenKhoanPhi) {
        this.tenKhoanPhi = tenKhoanPhi;
    }

    public Date getThoigiangiaodich() {
        return thoigiangiaodich;
    }

    public void setThoigiangiaodich(Date thoigiangiaodich) {
        this.thoigiangiaodich = thoigiangiaodich;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(thoigiangiaodich);
    }
    public  String getDecimalFormatsotiennop(){
        String pattern = "#,##0" + (getGiaTri() % 1 == 0 ? "" : "..#########");
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(giaTri);
    }
}
