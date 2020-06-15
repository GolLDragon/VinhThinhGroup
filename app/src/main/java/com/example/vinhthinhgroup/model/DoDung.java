package com.example.vinhthinhgroup.model;

import java.io.Serializable;

public class DoDung implements Serializable {
    public int MaDoDung;
    public String TenDoDung;
    public String Hinh;
    public int CongSuat;
    public int MaPhong;

    public DoDung(int maDoDung, String tenDoDung, String hinh, int congSuat, int maPhong) {
        MaDoDung = maDoDung;
        TenDoDung = tenDoDung;
        Hinh = hinh;
        CongSuat = congSuat;
        MaPhong = maPhong;
    }

    public int getMaDoDung() {
        return MaDoDung;
    }

    public void setMaDoDung(int maDoDung) {
        MaDoDung = maDoDung;
    }

    public String getTenDoDung() {
        return TenDoDung;
    }

    public void setTenDoDung(String tenDoDung) {
        TenDoDung = tenDoDung;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }

    public int getCongSuat() {
        return CongSuat;
    }

    public void setCongSuat(int congSuat) {
        CongSuat = congSuat;
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int maPhong) {
        MaPhong = maPhong;
    }
}
