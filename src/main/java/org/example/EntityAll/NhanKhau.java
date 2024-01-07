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
    private int gioiTinh;
    @Column(name = "quoc_tich", length = 50)
    private String quocTich;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "so_tang", referencedColumnName = "so_tang"),
            @JoinColumn(name = "so_phong", referencedColumnName = "id")
    })
    private HoKhau hoKhau;
    @Column(name = "trang_thai", length = 100)
    private String trangThai;

    @Column(name = "chu_ho")
    private boolean chuHo;

    @Column(name = "CCCD", length = 20)
    private String CCCD;

    @Column(name = "So_dien_thoai", length = 20)
    private String soDienThoai;

    public NhanKhau() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed


    public NhanKhau(String ten, Date ngaySinh, int gioiTinh, String quocTich, HoKhau hoKhau, String trangThai, boolean chuHo, String CCCD, String soDienThoai) {
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.quocTich = quocTich;
        this.hoKhau = hoKhau;
        this.trangThai = trangThai;
        this.chuHo = chuHo;
        this.CCCD = CCCD;
        this.soDienThoai = soDienThoai;
    }

    public HoKhau getHoKhau() {
        return hoKhau;
    }
    public void setHoKhau(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
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

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
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

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    public int getSophong(){return hoKhau.getId();}
    public int getSotang(){return hoKhau.getSoTang();}

}
