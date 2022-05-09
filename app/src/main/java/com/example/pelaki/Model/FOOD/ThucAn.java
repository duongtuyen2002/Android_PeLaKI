package com.example.pelaki.Model.FOOD;

public class ThucAn {
    int id, gia;
    String ten,hinhanh;

    public ThucAn(int id, String ten, int gia, String hinhanh) {
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

//    public int getIdloai() {
//        return idloai;
//    }
//
//    public void setIdloai(int idloai) {
//        this.idloai = idloai;
//    }

    public int getGia() {
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
