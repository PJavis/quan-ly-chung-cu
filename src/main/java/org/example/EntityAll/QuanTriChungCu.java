package org.example.EntityAll;


import jakarta.persistence.*;

@Entity
@Table(name = "quan_tri_chung_cu")
public class QuanTriChungCu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_nguoi_quan_tri", nullable = false, length = 255)
    private String tenNguoiQuanTri;

    public QuanTriChungCu() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed

    public QuanTriChungCu(int id, String tenNguoiQuanTri) {
        this.id = id;
        this.tenNguoiQuanTri = tenNguoiQuanTri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNguoiQuanTri() {
        return tenNguoiQuanTri;
    }

    public void setTenNguoiQuanTri(String tenNguoiQuanTri) {
        this.tenNguoiQuanTri = tenNguoiQuanTri;
    }
}
