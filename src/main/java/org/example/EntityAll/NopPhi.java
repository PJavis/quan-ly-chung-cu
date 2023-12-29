package org.example.EntityAll;

import jakarta.persistence.*;
import org.example.Hibernatedao.KhoanPhiDao;

import java.sql.Date;
import java.text.DecimalFormat;

@Entity
@Table(name = "nop_phi")
public class NopPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "id_khoan_phi", nullable = false)
    private int idKhoanPhi;


    @Column(name = "so_phong")
    private int soPhong;
    @Column(name = "gia_tri")
    private double giaTri;

    @Column(name = "so_tang")
    private int soTang;
    @Column(name = "dien_tich_phong", columnDefinition = "double precision")
    private double dienTichPhong;
    @Column(name="ten_chu-ho")
    private String tenchuho;

    @Column(name = "trang_thai_dong_phi")
    private boolean trangThaiDongPhi;

    @Column(name = "ngay_nop_phi")
    private Date ngayNopPhi;

    @Column(name = "so_tien_da_dong")
    private double soTienDaDong;


    public NopPhi() {
        // Default constructor required by Hibernate
    }

    public NopPhi(int id, int idKhoanPhi, int soPhong,int soTang, boolean trangThaiDongPhi, Date ngayNopPhi, double soTienDaDong) {
        this.id = id;
        this.idKhoanPhi = idKhoanPhi;
        this.soPhong = soPhong;
        this.soTang=soTang;
        this.trangThaiDongPhi = trangThaiDongPhi;
        this.ngayNopPhi = ngayNopPhi;
        this.soTienDaDong = soTienDaDong;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }



    public double getDienTichPhong() {
        return dienTichPhong;
    }

    public void setDienTichPhong(double dienTichPhong) {
        this.dienTichPhong = dienTichPhong;
    }

    public String getTenchuho() {
        return tenchuho;
    }

    public void setTenchuho(String tenchuho) {
        this.tenchuho = tenchuho;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public int getSoTang() {
        return soTang;
    }

    public void setSoTang(int soTang) {
        this.soTang = soTang;
    }

    public void setIdKhoanPhi(int idKhoanPhi) {
        this.idKhoanPhi = idKhoanPhi;
    }

    public int getIdKhoanPhi() {
        return idKhoanPhi;
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
    public double getSotienchuanop(){
        return dienTichPhong*giaTri-soTienDaDong;
    }
    public  String getDecimalFormatSotienchuanop(){
        double duno=dienTichPhong*giaTri-soTienDaDong;
        String pattern = "#,##0" + (duno % 1 == 0 ? "" : ".#########");
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(duno);
    }
    public  String getDecimalFormatSotienchuanopdonggop(){
        double duno=giaTri-soTienDaDong;
        String pattern = "#,##0" + (duno % 1 == 0 ? "" : ".#########");
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(duno);
    }
    public  String getDecimalFormatSotiendanop(){

        String pattern = "#,##0" + (soTienDaDong % 1 == 0 ? "" : ".#########");
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(soTienDaDong);
    }
    public String getTenKhoanPhi(){
        return KhoanPhiDao.getInstance().selectByid(idKhoanPhi).getTenKhoanPhi();
    }
    public String getLoaiKhoanPhi(){
        return KhoanPhiDao.getInstance().selectByid(idKhoanPhi).getLoaiKhoanPhi();
    }
    public KhoanPhi getKhoanphi(){
        return KhoanPhiDao.getInstance().selectByid(idKhoanPhi);
    }

}
