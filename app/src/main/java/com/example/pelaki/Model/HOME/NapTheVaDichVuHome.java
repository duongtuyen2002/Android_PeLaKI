package com.example.pelaki.Model.HOME;

public class NapTheVaDichVuHome {
    public int id;
    public String tendichvu,tenhang, anh;
    public Integer gia;

    public NapTheVaDichVuHome(int id, String tendichvu, String tenhang, Integer gia, String anh) {
        this.id = id;
        this.tendichvu = tendichvu;
        this.tenhang = tenhang;
        this.anh = anh;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTendichvu() {
        return tendichvu;
    }

    public void setTendichvu(String tendichvu) {
        this.tendichvu = tendichvu;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }
}
