package com.example.unitec.quanlynhanvien.DTO;

/**
 * Created by Unitec on 01/11/2017.
 */

public class PhongBanDTO {
    private int maPhongBan;
    private String tenPhongBan;
    private int soNhanVien;

    public PhongBanDTO() {
    }

    public int getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(int maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public int getSoNhanVien() {
        return soNhanVien;
    }

    public void setSoNhanVien(int soNhanVien) {
        this.soNhanVien = soNhanVien;
    }
}
