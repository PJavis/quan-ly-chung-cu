package org.example.EntityAll;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "nhan_khau")
public class NhanKhau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nguoi_dan")
    private int idNguoiDan;

    @Column(name = "ten", nullable = false)
    private String ten;

    @Column(name = "ngay_sinh", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaySinh;

    @Column(name = "gioi_tinh", nullable = false, length = 10)
    private String gioiTinh;

    @Column(name = "quoc_tich", length = 50)
    private String quocTich;

    @ManyToOne
    @JoinColumn(name = "ho_khau_id", referencedColumnName = "id")
    private HoKhau hoKhau;

    @Column(name = "trang_thai", length = 1000)
    private String trangThai;

    @Column(name = "chu_ho")
    private boolean chuHo;

    public NhanKhau() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed

    public NhanKhau(int idNguoiDan, String ten, Date ngaySinh, String gioiTinh, String quocTich, HoKhau hoKhau, String trangThai, boolean chuHo) {
        this.idNguoiDan = idNguoiDan;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.quocTich = quocTich;
        this.hoKhau = hoKhau;
        this.trangThai = trangThai;
        this.chuHo = chuHo;
    }

    public int getIdNguoiDan() {
        return idNguoiDan;
    }

    public void setIdNguoiDan(int idNguoiDan) {
        this.idNguoiDan = idNguoiDan;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isChuHo() {
        return chuHo;
    }

    public void setChuHo(boolean chuHo) {
        this.chuHo = chuHo;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public HoKhau getHoKhau() {
        return hoKhau;
    }

    public void setHoKhau(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
