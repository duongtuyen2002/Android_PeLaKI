package com.example.pelaki.Model.Acivity;

public class GioHang {
    int id;
    int soluong;
    long gia;
    String ten,hinhanh;

    public GioHang(int id, String ten,int soluong, long gia, String hinhanh) {
        this.id = id;
        this.soluong = soluong;
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


    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
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
