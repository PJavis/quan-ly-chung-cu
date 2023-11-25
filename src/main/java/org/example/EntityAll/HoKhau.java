package org.example.EntityAll;

import jakarta.persistence.*;
import java.io.Serializable;


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
    @Column(name="ten_chu-ho")
    private String tenchuho;


    // Default constructor required by Hibernate
    public HoKhau() {
    }

    // Parameterized constructor
    public HoKhau(int id, int soTang, double dienTichPhong,String tenchuho) {
        this.id = id;
        this.soTang = soTang;
        this.dienTichPhong = dienTichPhong;
        this.tenchuho=tenchuho;

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
}
