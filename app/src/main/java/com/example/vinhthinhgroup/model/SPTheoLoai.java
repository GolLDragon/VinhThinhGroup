package com.example.vinhthinhgroup.model;

import java.io.Serializable;

public class SPTheoLoai implements Serializable {
    public int MaSP;
    public String TenSP;
    public String DVT;
    public int Gia;
    public String TinhNang;
    public int SoLuong;
    public String GhiChu;
    public String Hinh;

    public SPTheoLoai(int maSP, String tenSP, String DVT, int gia, String tinhNang, int soLuong, String ghiChu, String hinh) {
        MaSP = maSP;
        TenSP = tenSP;
        this.DVT = DVT;
        Gia = gia;
        TinhNang = tinhNang;
        SoLuong = soLuong;
        GhiChu = ghiChu;
        Hinh = hinh;
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

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public String getTinhNang() {
        return TinhNang;
    }

    public void setTinhNang(String tinhNang) {
        TinhNang = tinhNang;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }
}
