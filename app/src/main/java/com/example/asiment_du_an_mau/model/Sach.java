package com.example.asiment_du_an_mau.model;

public class Sach {
    public int maSach;
    public String tenSach;
    public int giaThue;
    public int maLoai;

    public Sach(int maSach, String tenSach, int giaThue, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
    }

    public Sach() {
    }
}
