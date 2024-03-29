package org.example.Model.EntityAll;

import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name="so_dien_nuoc")
    private double sodiennuoc;
    @Column(name = "gia_tri")
    private double giaTri;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "so_phong", referencedColumnName = "id"),
            @JoinColumn(name = "so_tang", referencedColumnName = "so_tang")
    })
    private HoKhau hoKhau;

    @OneToMany(mappedBy = "nopPhi", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LichSuGiaoDich> lichSuGiaoDichs = new HashSet<>();


    @Column(name = "trang_thai_dong_phi")
    private boolean trangThaiDongPhi;

    public int getId() {
        return id;
    }

    @Column(name = "so_tien_da_dong")
    private double soTienDaDong;

    public NopPhi() {
        // Default constructor required by Hibernate
    }

    public NopPhi(KhoanPhi khoanPhi, double giaTri, HoKhau hoKhau, boolean trangThaiDongPhi, double soTienDaDong, Set<LichSuGiaoDich> lichsugiaodiches) {
        this.khoanPhi = khoanPhi;
        this.giaTri = giaTri;
        this.hoKhau = hoKhau;
        this.trangThaiDongPhi = trangThaiDongPhi;
        this.lichSuGiaoDichs=lichsugiaodiches;
        this.soTienDaDong = soTienDaDong;
    }

    public double getSodiennuoc() {
        return sodiennuoc;
    }

    public void setSodiennuoc(double sodiennuoc) {
        this.sodiennuoc = sodiennuoc;
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



    public int getIdKhoanPhi() {
        return this.khoanPhi.getId();
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
    public String getTenchuho(){
        return this.hoKhau.getTenchuho();
    }
    public  String getDecimalFormatsotien(){
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
        return khoanPhi.getTenKhoanPhi();
    }
    public String getLoaiKhoanPhi(){
        return this.khoanPhi.getLoaiKhoanPhi();
    }
    public int getSoTang() {
        return this.getHoKhau().getSoTang();

    }

    public int getSoPhong() {
        return this.getHoKhau().getId();
    }
}
