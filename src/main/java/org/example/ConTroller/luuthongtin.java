package org.example.ConTroller;
public class luuthongtin {
    private static String taiKhoan;
    private static String matKhau;

    public static void setThongTinDangNhap(String taiKhoan, String matKhau) {
        luuthongtin.taiKhoan = taiKhoan;
        luuthongtin.matKhau = matKhau;
    }

    public static String getTaiKhoan() {
        return taiKhoan;
    }

    public static String getMatKhau() {
        return matKhau;
    }
}
