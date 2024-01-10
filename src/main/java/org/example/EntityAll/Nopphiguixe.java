package org.example.EntityAll;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nop_phi")
public class Nopphiguixe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "gia_tri")
    private double giaTri;

    @OneToOne
    @JoinColumn(name = "id_phuong_tien", nullable = false)
    private PhuongTien phuongTien;


    @Column(name = "trang_thai_dong_phi")
    private boolean trangThaiDongPhi;

    public int getId() {
        return id;
    }

    @Column(name = "so_tien_da_dong")
    private double soTienDaDong;

    public Nopphiguixe() {
        // Default constructor required by Hibernate
    }

}