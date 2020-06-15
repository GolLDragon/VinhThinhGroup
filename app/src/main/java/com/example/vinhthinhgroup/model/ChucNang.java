package com.example.vinhthinhgroup.model;

import java.io.Serializable;

public class ChucNang implements Serializable {
    public int MaChucNang;
    public String TenChucNang;
    public String Hinh;

    public ChucNang(int maChucNang, String tenChucNang, String hinh) {
        MaChucNang = maChucNang;
        TenChucNang = tenChucNang;
        Hinh = hinh;
    }

    public int getMaChucNang() {
        return MaChucNang;
    }

    public void setMaChucNang(int maChucNang) {
        MaChucNang = maChucNang;
    }

    public String getTenChucNang() {
        return TenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        TenChucNang = tenChucNang;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }
}
