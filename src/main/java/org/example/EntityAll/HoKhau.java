package org.example.EntityAll;

import jakarta.persistence.*;

@Entity
@Table(name = "ho_khau")
public class HoKhau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_nguoidan", nullable = false)
    private int idNguoiDan;

    @Column(name = "dien_tich_phong", precision = 5, scale = 2, nullable = false)
    private double dienTichPhong;

    @Column(name = "chu_ho", nullable = false)
    private String chuHo;

    public HoKhau() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNguoiDan() {
        return idNguoiDan;
    }

    public void setIdNguoiDan(int idNguoiDan) {
        this.idNguoiDan = idNguoiDan;
    }

    public double getDienTichPhong() {
        return dienTichPhong;
    }

    public void setDienTichPhong(double dienTichPhong) {
        this.dienTichPhong = dienTichPhong;
    }

    public String getChuHo() {
        return chuHo;
    }

    public void setChuHo(String chuHo) {
        this.chuHo = chuHo;
    }
}
