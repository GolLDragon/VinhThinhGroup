package com.example.vinhthinhgroup.model;

import java.io.Serializable;

public class SLPhong implements Serializable {
    public static int slphongkhack;
    public static int slphongan;
    public static int slphongngu;

    public SLPhong(int slphongkhack, int slphongan, int slphongngu) {
        this.slphongkhack = slphongkhack;
        this.slphongan = slphongan;
        this.slphongngu = slphongngu;
    }

    public static int getSlphongkhack() {
        return slphongkhack;
    }

    public void setSlphongkhack(int slphongkhack) {
        this.slphongkhack = slphongkhack;
    }

    public static int getSlphongan() {
        return slphongan;
    }

    public void setSlphongan(int slphongan) {
        this.slphongan = slphongan;
    }

    public static int getSlphongngu() {
        return slphongngu;
    }

    public void setSlphongngu(int slphongngu) {
        this.slphongngu = slphongngu;
    }
}
