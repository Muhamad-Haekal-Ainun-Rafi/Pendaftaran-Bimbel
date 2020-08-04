package com.lailatul.bimbel.model;

import java.io.Serializable;

public class Request implements Serializable {

    private String nama;
    private String tanggal_lahir;
    private String sekolah;
    private String alamat;
    private String email;
    private String foto;
    private String key;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }



    public Request(){

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() { return alamat; }

    public void setAlamat(String nim) { this.alamat = alamat; }

    public String getTanggal_lahir() { return tanggal_lahir; }

    public void setTanggal_lahir(String nim) { this.tanggal_lahir = tanggal_lahir; }

    public String getSekolah() { return sekolah; }

    public void setSekolah(String kelas) {
        this.sekolah = sekolah;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() { return key; }

    public void setKey(String key) {
        this.key = key;
    }

    public Request(String nama, String alamat, String tanggal_lahir, String sekolah, String email, String foto) {
        this.nama = nama;
        this.alamat = alamat;
        this.tanggal_lahir = tanggal_lahir;
        this.sekolah = sekolah;
        this.email = email;
        this.foto = foto;
    }


    @Override
    public String toString(){
        return ""+nama+"\n"+
                ""+alamat+"\n"+
                ""+tanggal_lahir+"\n"+
                ""+sekolah+"\n"+
                ""+email;
    }


}
