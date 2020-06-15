package com.example.vinhthinhgroup.model;

import java.io.Serializable;

public class Nhom implements Serializable {
    public int MaNhom;
    public String TenNhom;
    public String Hinh;

    public Nhom(int maNhom, String tenNhom, String hinh) {
        MaNhom = maNhom;
        TenNhom = tenNhom;
        Hinh = hinh;
    }

    public int getMaNhom() {
        return MaNhom;
    }

    public void setMaNhom(int maNhom) {
        MaNhom = maNhom;
    }

    public String getTenNhom() {
        return TenNhom;
    }

    public void setTenNhom(String tenNhom) {
        TenNhom = tenNhom;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }
}
