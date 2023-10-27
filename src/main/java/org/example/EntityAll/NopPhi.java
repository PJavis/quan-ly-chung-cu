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
    private DanhSachKhoanPhi idKhoanPhi;

    @ManyToOne
    @JoinColumn(name = "ho_khau_id", referencedColumnName = "id")
    private HoKhau hoKhau;

    @Column(name = "trang_thai_dong_phi")
    private boolean trangThaiDongPhi;

    @Column(name = "ngay_nop_phi")
    private Date ngayNopPhi;


    public NopPhi() {
        // Default constructor required by Hibernate
    }

    public NopPhi(DanhSachKhoanPhi idKhoanPhi, HoKhau hoKhau, boolean trangThaiDongPhi, Date ngayNopPhi) {
        this.idKhoanPhi = idKhoanPhi;
        this.hoKhau = hoKhau;
        this.trangThaiDongPhi = trangThaiDongPhi;
        this.ngayNopPhi = ngayNopPhi;
    }

    public int getId() {
        return id;
    }

    public DanhSachKhoanPhi getIdKhoanPhi() {
        return idKhoanPhi;
    }

    public HoKhau getHoKhau() {
        return hoKhau;
    }
    public void setHoKhau(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
    }

    public boolean isTrangThaiDongPhi() {
        return trangThaiDongPhi;
    }

    public void setTrangThaiDongPhi(boolean trangThaiDongPhi) {
        this.trangThaiDongPhi = trangThaiDongPhi;
    }

    public Date getNgay_nop_phi() {
        return ngay_nop_phi;
    }

    public void setNgay_nop_phi(Date ngay_nop_phi) {
        this.ngay_nop_phi = ngay_nop_phi;
    }
}
