package org.example.EntityAll;

import jakarta.persistence.*;

@Entity
@Table(name = "ho_khau")
public class HoKhau {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "dien_tich_phong", columnDefinition = "double precision")
    private double dienTichPhong;
    @Id
    @Column(name = "so_tang")
    private int sotang;
    public HoKhau() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed

    public HoKhau(int id, double dienTichPhong,int sotang) {
        this.id = id;
        this.dienTichPhong = dienTichPhong;
        this.sotang=sotang;
    }

    public int getSotang() {
        return sotang;
    }

    public void setSotang(int sotang) {
        this.sotang = sotang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDienTichPhong() {
        return dienTichPhong;
    }

    public void setDienTichPhong(double dienTichPhong) {
        this.dienTichPhong = dienTichPhong;
    }
}
