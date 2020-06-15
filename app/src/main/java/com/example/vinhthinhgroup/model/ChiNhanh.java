package com.example.vinhthinhgroup.model;

import java.io.Serializable;

public class ChiNhanh implements Serializable {
    public int MaChiNhanh;
    public String DiaChi;
    public String SDT;
    public String Mail;
    public double v1;
    public double v2;

    public ChiNhanh(int maChiNhanh, String diaChi, String SDT, String mail, double v1, double v2) {
        MaChiNhanh = maChiNhanh;
        DiaChi = diaChi;
        this.SDT = SDT;
        Mail = mail;
        this.v1 = v1;
        this.v2 = v2;
    }

    public int getMaChiNhanh() {
        return MaChiNhanh;
    }

    public void setMaChiNhanh(int maChiNhanh) {
        MaChiNhanh = maChiNhanh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public double getV1() {
        return v1;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }

    public double getV2() {
        return v2;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }
}
