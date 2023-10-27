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

    public HoKhau() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed

    public HoKhau(int id, double dienTichPhong) {
        this.id = id;
        this.dienTichPhong = dienTichPhong;
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
