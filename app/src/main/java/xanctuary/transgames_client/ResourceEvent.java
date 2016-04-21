package xanctuary.transgames_client;

import com.google.gson.annotations.SerializedName;

public class ResourceEvent{

    @SerializedName("nama_event")
    private String nama;

    @SerializedName("harga_event")
    private String[] harga;


    @SerializedName("nominal_event")
    private String[] nominal;

    public ResourceEvent() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String[] getHarga() {
        return harga;
    }

    public void setHarga(String[] harga) {
        this.harga = harga;
    }

    public String[] getNominal() {
        return nominal;
    }

    public void setNominal(String[] nominal) {
        this.nominal = nominal;
    }
}
