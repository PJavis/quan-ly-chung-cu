package org.example.EntityAll;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Embeddable
class HoKhauId implements Serializable {
    private int id;
    private int soTang;

    // constructors, equals, hashCode methods as needed

    // getters and setters
}

@Entity
@Table(name = "ho_khau")
@IdClass(HoKhauId.class)
public class HoKhau {
    @Id
    @Column(name = "id")
    private int id;
    @Id
    @Column(name = "so_tang")
    private int soTang;
    @Column(name = "dien_tich_phong", columnDefinition = "double precision")
    private double dienTichPhong;
    @Column(name="ten_chu_ho")
    private String tenchuho;
    @Column(name="ngay_tao_ho_khau")
    private Date ngaytaohokhau;

    @Column(name = "So_dien_thoai")
    private String soDienThoai;

    @Column(name = "So_nhan_khau")
    private int soNhanKhau;

    @OneToMany(mappedBy = "hoKhau", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NopPhi> nopPhis = new HashSet<>();

    @OneToMany(mappedBy = "hoKhau", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NhanKhau> nhanKhaus = new HashSet<>();

    @OneToMany(mappedBy = "hoKhau", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PhuongTien> phuongTiens = new HashSet<>();

    @OneToMany(mappedBy = "hoKhau", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LichSuThayDoi> lichSuThayDois = new HashSet<>();

    // Default constructor required by Hibernate
    public HoKhau() {
        // Other initialization if needed
    }

    // Parameterized constructor
    public HoKhau(int id, int soTang, double dienTichPhong, String tenchuho, Date ngaytaohokhau) {
        this.id = id;
        this.soTang = soTang;
        this.dienTichPhong = dienTichPhong;
        this.tenchuho = tenchuho;
        this.ngaytaohokhau =ngaytaohokhau;
        // No need to set soNhanKhau here
    }
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public Set<NopPhi> getNopPhis() {
        return nopPhis;
    }

    public void setNopPhis(Set<NopPhi> nopPhis) {
        this.nopPhis = nopPhis;
    }

    public Set<NhanKhau> getNhanKhaus() {
        return nhanKhaus;
    }

    public void setNhanKhaus(Set<NhanKhau> nhanKhaus) {
        this.nhanKhaus = nhanKhaus;
    }

    public Set<PhuongTien> getPhuongTiens() {
        return phuongTiens;
    }

    public void setPhuongTiens(Set<PhuongTien> phuongTiens) {
        this.phuongTiens = phuongTiens;
    }

    public Set<LichSuThayDoi> getLichSuThayDois() {
        return lichSuThayDois;
    }

    public void setLichSuThayDois(Set<LichSuThayDoi> lichSuThayDois) {
        this.lichSuThayDois = lichSuThayDois;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoTang() {
        return soTang;
    }

    public void setSoTang(int soTang) {
        this.soTang = soTang;
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

    public Date getNgaytaohokhau() {
        return ngaytaohokhau;
    }

    public void setNgaytaohokhau(Date ngaytaohokhau) {
        this.ngaytaohokhau = ngaytaohokhau;
    }

    public int getSoNhanKhau() {
        return soNhanKhau;
    }

    public void setSoNhanKhau(int soNhanKhau) {
        this.soNhanKhau = soNhanKhau;
    }
}
