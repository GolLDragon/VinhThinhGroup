package com.example.vinhthinhgroup.model;

import java.io.Serializable;

public class GoiYThietBi implements Serializable {
    public int MaThietBi;
    public String TenThietBi;
    public String Hinh;

    public GoiYThietBi(int maThietBi, String tenThietBi, String hinh) {
        MaThietBi = maThietBi;
        TenThietBi = tenThietBi;
        Hinh = hinh;
    }

    public int getMaThietBi() {
        return MaThietBi;
    }

    public void setMaThietBi(int maThietBi) {
        MaThietBi = maThietBi;
    }

    public String getTenThietBi() {
        return TenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        TenThietBi = tenThietBi;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }
}
