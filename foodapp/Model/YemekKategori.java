package com.example.foodapp.Model;

public class YemekKategori {
    public String kategori_title;
    public String kategori_icerik;
    public int img_src;

    public YemekKategori(String kategori_title, String kategori_icerik, int img_src) {
        this.kategori_title = kategori_title;
        this.kategori_icerik = kategori_icerik;
        this.img_src = img_src;
    }

    public String getKategori_title() {
        return kategori_title;
    }

    public void setKategori_title(String kategori_title) {
        this.kategori_title = kategori_title;
    }

    public String getKategori_icerik() {
        return kategori_icerik;
    }

    public void setKategori_icerik(String kategori_icerik) {
        this.kategori_icerik = kategori_icerik;
    }

    public int getImg_src() {
        return img_src;
    }

    public void setImg_src(int img_src) {
        this.img_src = img_src;
    }



}
