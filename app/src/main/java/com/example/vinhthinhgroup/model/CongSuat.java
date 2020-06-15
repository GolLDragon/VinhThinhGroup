package com.example.vinhthinhgroup.model;

public class CongSuat {
    public int MaDoDung;
    public String TenDoDung;
    public String Hinh;
    public int CongSuat;
    public int soluong;
    public int MaPhong;

    public CongSuat(int maDoDung, String tenDoDung, String hinh, int congSuat, int soluong, int maPhong) {
        MaDoDung = maDoDung;
        TenDoDung = tenDoDung;
        Hinh = hinh;
        CongSuat = congSuat;
        this.soluong = soluong;
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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int maPhong) {
        MaPhong = maPhong;
    }
}
