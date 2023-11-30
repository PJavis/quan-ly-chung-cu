package org.example.EntityAll;

import jakarta.persistence.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

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

    private Date ngaySinh;

    @Column(name = "gioi_tinh", nullable = false, length = 10)
    private String gioiTinh;
    @Column(name = "quoc_tich", length = 50)
    private String quocTich;

    @Column(name = "so_phong")
    private int sophong;
    @Column(name ="so_tang")
    private int sotang;

    @Column(name = "trang_thai", length = 1000)
    private String trangThai;

    @Column(name = "chu_ho")
    private boolean chuHo;

    public NhanKhau() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed

    public NhanKhau(int idNguoiDan, String ten, Date ngaySinh, String gioiTinh, String quocTich, int sophong,int sotang, String trangThai, boolean chuHo) {
        this.idNguoiDan = idNguoiDan;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.quocTich = quocTich;
        this.sophong = sophong;
        this.sotang=sotang;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(ngaySinh);
    }
}
