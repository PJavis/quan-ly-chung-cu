package org.example.Model.EntityAll;


import jakarta.persistence.*;

@Entity
@Table(name = "tai_khoan_bqt")
public class TaiKhoanBQT {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "tai_khoan", nullable = false, length = 50)
    private String taiKhoan;

    @Column(name = "mat_khau", nullable = false, length = 50)
    private String matKhau;

    @Column(name = "id_nguoi_quan_tri", nullable = false)
    private int idNguoiQuanTri;

    @OneToOne
    @JoinColumn(name = "id_nguoi_quan_tri", referencedColumnName = "id", insertable = false, updatable = false)
    private QuanTriChungCu quanTriChungCu;

    public TaiKhoanBQT() {
        // Default constructor required by Hibernate
    }

    // Constructors, getters, setters, and other methods as needed

    public TaiKhoanBQT(int id, String taiKhoan, String matKhau, int idNguoiQuanTri, QuanTriChungCu quanTriChungCu) {
        this.id = id;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.idNguoiQuanTri = idNguoiQuanTri;
        this.quanTriChungCu = quanTriChungCu;
    }

    public int getId() {
        return id;
    }
    public void setid(int id){
        this.id = id;
    }
    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getIdNguoiQuanTri() {
        return idNguoiQuanTri;
    }

    public void setIdNguoiQuanTri(int idNguoiQuanTri) {
        this.idNguoiQuanTri = idNguoiQuanTri;
    }

    public QuanTriChungCu getQuanTriChungCu() {
        return quanTriChungCu;
    }

    public void setQuanTriChungCu(QuanTriChungCu quanTriChungCu) {
        this.quanTriChungCu = quanTriChungCu;
    }
}
