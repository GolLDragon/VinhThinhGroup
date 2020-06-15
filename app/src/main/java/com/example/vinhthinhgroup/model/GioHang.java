package com.example.vinhthinhgroup.model;

public class GioHang {
    public int MaSP;
    public String TenSP;
    public long Gia;
    public String Hinh;
    public int soluong;

    public GioHang(int maSP, String tenSP, long gia, String hinh, int soluong) {
        MaSP = maSP;
        TenSP = tenSP;
        Gia = gia;
        Hinh = hinh;
        this.soluong = soluong;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public long getGia() {
        return Gia;
    }

    public void setGia(long gia) {
        Gia = gia;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
