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


    @ManyToOne
    @JoinColumn(name = "id_khoan_phi", nullable = false)
    private KhoanPhi khoanPhi;

    @Column(name = "gia_tri")
    private double giaTri;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "so_phong", referencedColumnName = "id"),
            @JoinColumn(name = "so_tang", referencedColumnName = "so_tang")
    })
    private HoKhau hoKhau;

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

    public NopPhi(KhoanPhi khoanPhi, double giaTri, HoKhau hoKhau, String tenchuho, boolean trangThaiDongPhi, Date ngayNopPhi, double soTienDaDong) {
        this.khoanPhi = khoanPhi;
        this.giaTri = giaTri;
        this.hoKhau = hoKhau;
        this.tenchuho = tenchuho;
        this.trangThaiDongPhi = trangThaiDongPhi;
        this.ngayNopPhi = ngayNopPhi;
        this.soTienDaDong = soTienDaDong;
    }

    public KhoanPhi getKhoanPhi() {
        return khoanPhi;
    }

    public void setKhoanPhi(KhoanPhi khoanPhi) {
        this.khoanPhi = khoanPhi;
    }

    public HoKhau getHoKhau() {
        return hoKhau;
    }

    public void setHoKhau(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }





    public String getTenchuho() {
        return tenchuho;
    }

    public void setTenchuho(String tenchuho) {
        this.tenchuho = tenchuho;
    }


    public int getIdKhoanPhi() {
        return this.khoanPhi.getId();
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
        return giaTri-soTienDaDong;
    }
    public  String getDecimalFormatSotien(){
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
        return KhoanPhiDao.getInstance().selectByid(this.khoanPhi.getId()).getTenKhoanPhi();
    }
    public String getLoaiKhoanPhi(){
        return KhoanPhiDao.getInstance().selectByid(this.khoanPhi.getId()).getLoaiKhoanPhi();
    }
    public int getSoTang() {
        return this.getHoKhau().getSoTang();

    }

    public int getSoPhong() {
        return this.getHoKhau().getId();
    }
}
