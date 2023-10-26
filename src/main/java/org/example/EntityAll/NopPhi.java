package org.example.EntityAll;

import jakarta.persistence.*;

@Entity
@Table(name = "nop_phi")
public class NopPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_khoan_phi", nullable = false, length = 255)
    private int id_khoan_phi;

    @ManyToOne
    @JoinColumn(name = "ho_khau_id", referencedColumnName = "id")
    private HoKhau hoKhau;

    public NopPhi() {
        // Default constructor required by Hibernate
    }

    public NopPhi(int id, int id_khoan_phi, HoKhau hoKhau) {
        this.id = id;
        this.id_khoan_phi = id_khoan_phi;
        this.hoKhau = hoKhau;
    }

    public int getId() {
        return id;
    }

    public int getId_khoan_phi() {
        return id_khoan_phi;
    }

    public HoKhau getHoKhau() {
        return hoKhau;
    }

    public void setId_khoan_phi(int id_khoan_phi) {
        this.id_khoan_phi = id_khoan_phi;
    }

    public void setHoKhau(HoKhau hoKhau) {
        this.hoKhau = hoKhau;
    }
}
