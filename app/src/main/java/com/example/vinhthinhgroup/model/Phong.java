package com.example.vinhthinhgroup.model;

import java.io.Serializable;

public class Phong implements Serializable {
    public int MaPhong;
    public String TenPhong;
    public String Hinh;

    public Phong(int maPhong, String tenPhong, String hinh) {
        MaPhong = maPhong;
        TenPhong = tenPhong;
        Hinh = hinh;
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int maPhong) {
        MaPhong = maPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }
}
