package com.example.pelaki.Model.Acivity;

public class DienThoai {
    int id;
    long gia;
    String ten,hinhanh;

    public DienThoai(int id, long gia, String ten, String hinhanh) {
        this.id = id;
        this.gia = gia;
        this.ten = ten;
        this.hinhanh = hinhanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
