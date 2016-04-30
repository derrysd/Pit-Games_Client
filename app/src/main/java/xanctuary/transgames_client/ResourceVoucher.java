package xanctuary.transgames_client;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResourceVoucher {
    private String id;
    private String title;
    private String info;
    private String available;
    private String remark;

    @SerializedName("voucerDetails")
    private VoucherDetails[] voucherDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ArrayList<String> getListNominal(){
        ArrayList<String> listNominal = new ArrayList<>();
        listNominal.add("PILIH NOMINAL");
        for (VoucherDetails voucherDetail : voucherDetails) {
            listNominal.add(String.valueOf(voucherDetail.getNominal()));
        }
        return listNominal;
    }

    public ArrayList<String> getListPrice(){
        ArrayList<String> listPrice = new ArrayList<>();
        for(VoucherDetails voucherDetail : voucherDetails){
            listPrice.add(String.valueOf(voucherDetail.getPrice()));
        }
        return listPrice;
    }

    public ArrayList<String> getListStock(){
        ArrayList<String> listStock = new ArrayList<>();
        for(VoucherDetails voucherDetail : voucherDetails){
            listStock.add(String.valueOf(voucherDetail.getStock()));
        }
        return listStock;
    }


    public VoucherDetails[] getVoucherDetails() {
        return voucherDetails;
    }

    public void setVoucherDetails(VoucherDetails[] voucherDetails) {
        this.voucherDetails = voucherDetails;
    }



}
