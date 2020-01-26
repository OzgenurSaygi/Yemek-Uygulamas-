package com.example.foodapp.Model;

public class Kullanici {
    private  String kullanici_id;
    private String kullanici_ad;
    private String kullanici_email;
    private  String resimurl;
    public Kullanici(){

    }
    public Kullanici(String kullanici_id,String kullanici_email,String kullanici_ad,String resimurl) {
        this.kullanici_id = kullanici_id;
        this.kullanici_ad = kullanici_ad;
        this.kullanici_email = kullanici_email;
        this.resimurl=resimurl;
    }
    public String getResimurl() {
        return resimurl;
    }

    public void setResimurl(String resimurl) {
        this.resimurl = resimurl;
    }


    public String getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(String kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getKullanici_ad() {
        return kullanici_ad;
    }

    public void setKullanici_ad(String kullanici_ad) {
        this.kullanici_ad = kullanici_ad;
    }

    public String getKullanici_email() {
        return kullanici_email;
    }

    public void setKullanici_email(String kullanici_email) {
        this.kullanici_email = kullanici_email;
    }
}
