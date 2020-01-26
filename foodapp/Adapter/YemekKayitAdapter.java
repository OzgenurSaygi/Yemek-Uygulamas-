package com.example.foodapp.Adapter;

public class YemekKayitAdapter {
    String eat_ad;
    String materials;
    String recipe;
    String trick;
    String link;
    String yemektur;
    String kisi_sayisi;
    String pisirme_suresi;
    String resimurl;
    String kullanici;
    String kategoriadi;

    public String getEat_ad() {
        return eat_ad;
    }

    public void setEat_ad(String eat_ad) {
        this.eat_ad = eat_ad;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getTrick() {
        return trick;
    }

    public void setTrick(String trick) {
        this.trick = trick;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getYemektur() {
        return yemektur;
    }

    public void setYemektur(String yemektur) {
        this.yemektur = yemektur;
    }

    public String getKisi_sayisi() {
        return kisi_sayisi;
    }

    public void setKisi_sayisi(String kisi_sayisi) {
        this.kisi_sayisi = kisi_sayisi;
    }

    public String getPisirme_suresi() {
        return pisirme_suresi;
    }

    public void setPisirme_suresi(String pisirme_suresi) {
        this.pisirme_suresi = pisirme_suresi;
    }

    public String getResimurl() {
        return resimurl;
    }

    public void setResimurl(String resimurl) {
        this.resimurl = resimurl;
    }

    public String getKullanici() {
        return kullanici;
    }

    public void setKullanici(String kullanici) {
        this.kullanici = kullanici;
    }

    public String getKategoriadi() {
        return kategoriadi;
    }

    public void setKategoriadi(String kategoriadi) {
        this.kategoriadi = kategoriadi;
    }


    public YemekKayitAdapter() {
    }

    public YemekKayitAdapter(String eat_ad, String materials, String recipe, String trick, String link, String yemektur, String kisi_sayisi, String pisirme_suresi, String resimurl, String kullanici, String kategoriadi) {
        this.eat_ad = eat_ad;
        this.materials = materials;
        this.recipe = recipe;
        this.trick = trick;
        this.link = link;
        this.yemektur = yemektur;
        this.kisi_sayisi = kisi_sayisi;
        this.pisirme_suresi = pisirme_suresi;
        this.resimurl = resimurl;
        this.kullanici = kullanici;
        this.kategoriadi = kategoriadi;
    }
}
