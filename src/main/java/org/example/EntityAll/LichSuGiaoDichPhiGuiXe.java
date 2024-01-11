package org.example.EntityAll;

import jakarta.persistence.*;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "lich_su_giao_dich")
public class LichSuGiaoDichPhiGuiXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="ten_nguoi_nop")
    private String tennguoinop;

    @ManyToOne
    @JoinColumn(name = "id_phuong_tien")
    private PhuongTien phuongTien;

    @Column(name = "thoi_gian_giao_dich")
    private Date thoigiangiaodich;

    @Column(name = "gia_tri")
    private double giaTri;
    public  LichSuGiaoDichPhiGuiXe(){

    }

    public LichSuGiaoDichPhiGuiXe(String tennguoinop, PhuongTien phuongTien, Date thoigiangiaodich, double giaTri) {
        this.tennguoinop = tennguoinop;

        this.phuongTien = phuongTien;
        this.thoigiangiaodich = thoigiangiaodich;
        this.giaTri = giaTri;
    }


    public PhuongTien getPhuongTien() {
        return phuongTien;
    }

    public void setPhuongTien(PhuongTien phuongTien) {
        this.phuongTien = phuongTien;
    }

    public String getTennguoinop() {
        return tennguoinop;
    }

    public void setTennguoinop(String tennguoinop) {
        this.tennguoinop = tennguoinop;
    }



    public Date getThoigiangiaodich() {
        return thoigiangiaodich;
    }

    public void setThoigiangiaodich(Date thoigiangiaodich) {
        this.thoigiangiaodich = thoigiangiaodich;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(thoigiangiaodich);
    }
    public  String getDecimalFormatsotiennop(){
        String pattern = "#,##0" + (getGiaTri() % 1 == 0 ? "" : ".#########");
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(giaTri);
    }
}
