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
    @ManyToOne
    @JoinColumn(name = "id_khoan_phi", referencedColumnName = "id", nullable = false)
    private DanhSachKhoanPhi danhSachKhoanPhi;

    @ManyToOne
    @JoinColumn(name = "ho_khau_id", referencedColumnName = "id")
    private HoKhau hoKhau;

    @Column(name = "trang_thai_dong_phi")
    private boolean trang_thai_dong_phi;

    @Column(name = "ngay_nop_phi")
    private Date ngay_nop_phi;


    public NopPhi() {
        // Default constructor required by Hibernate
    }

    public NopPhi(DanhSachKhoanPhi danhSachKhoanPhi, HoKhau hoKhau, boolean trang_thai_dong_phi, Date ngay_nop_phi) {
        this.danhSachKhoanPhi = danhSachKhoanPhi;
        this.hoKhau = hoKhau;
        this.trang_thai_dong_phi = trang_thai_dong_phi;
        this.ngay_nop_phi = ngay_nop_phi;
    }

    public DanhSachKhoanPhi getDanhSachKhoanPhi() {
        return danhSachKhoanPhi;
    }

    public void setDanhSachKhoanPhi(DanhSachKhoanPhi danhSachKhoanPhi) {
        this.danhSachKhoanPhi = danhSachKhoanPhi;
    }

    public int getId() {
        return id;
    }



    public HoKhau getHoKhau() {
        return hoKhau;
    }
    public void setHoKhau(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
    }

    public boolean isTrang_thai_dong_phi() {
        return trang_thai_dong_phi;
    }

    public void setTrang_thai_dong_phi(boolean trang_thai_dong_phi) {
        this.trang_thai_dong_phi = trang_thai_dong_phi;
    }

    public Date getNgay_nop_phi() {
        return ngay_nop_phi;
    }

    public void setNgay_nop_phi(Date ngay_nop_phi) {
        this.ngay_nop_phi = ngay_nop_phi;
    }
}
