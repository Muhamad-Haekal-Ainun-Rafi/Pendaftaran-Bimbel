package com.lailatul.bimbel.Pendaftaran;
import android.icu.lang.UProperty;

import java.io.Serializable;

public class PendaftaranReq implements Serializable {

    private String nama_lengkap;
    private String nama_panggilan;
    private String jenis_kelamin;
    private String tanggal_lahir;
    private String nama_ayah;
    private String no_telpon;
    private String alamat;
    private String kabupaten;
    private String provinsi;
    private String asal_sekolah;
    private String paket_bimbel;
    private String key;
    private String userId;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) { this.date = date; }

    public String getNama_lengkap() { return nama_lengkap; }

    public void setNama_lengkap(String nama_lengkap) { this.nama_lengkap = nama_lengkap;    }

    public String getNama_panggilan() {
        return nama_panggilan;
    }

    public void setNama_panggilan(String nama_panggilan) {
        this.nama_panggilan = nama_panggilan;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getNama_ayah() { return nama_ayah;    }

    public void setNama_ayah(String nama_ayah) {this.nama_ayah = nama_ayah; }

    public String getNo_telpon() { return no_telpon;    }

    public void setNo_telpon(String no_telpon) {this.no_telpon = no_telpon; }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getProvinsi() { return provinsi;    }

    public void setProvinsi(String provinsi) {this.provinsi = provinsi; }

    public String getAsal_sekolah() { return asal_sekolah;    }

    public void setAsal_sekolah(String asal_sekolah) {this.asal_sekolah = asal_sekolah; }

    public String getPaket_bimbel() { return paket_bimbel;    }

    public void setPaket_bimbel(String paket_bimbel) {this.paket_bimbel = paket_bimbel; }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PendaftaranReq(){}

    public PendaftaranReq(String nama_lengkap, String nama_panggilan, String jenis_kelamin, String tanggal_lahir, String nama_ayah, String no_telpon, String alamat, String kabupaten, String provinsi, String asal_sekolah, String paket_bimbel) {
        this.nama_lengkap = nama_lengkap;
        this.nama_panggilan = nama_panggilan;
        this.jenis_kelamin = jenis_kelamin;
        this.tanggal_lahir = tanggal_lahir;
        this.nama_ayah = nama_ayah;
        this.no_telpon = no_telpon;
        this.alamat = alamat;
        this.kabupaten = kabupaten;
        this.provinsi = provinsi;
        this.asal_sekolah = asal_sekolah;
        this.paket_bimbel = paket_bimbel;

    }

}



