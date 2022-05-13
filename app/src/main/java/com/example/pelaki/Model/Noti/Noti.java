package com.example.pelaki.Model.Noti;

public class Noti {
    int id;
    String thongbao, noidung,img;

    public Noti(int id, String thongbao, String noidung, String img) {
        this.id = id;
        this.thongbao = thongbao;
        this.noidung = noidung;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThongbao() {
        return thongbao;
    }

    public void setThongbao(String thongbao) {
        this.thongbao = thongbao;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
