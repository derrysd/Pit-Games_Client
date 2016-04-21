package xanctuary.transgames_client;


public class ResourceVoucher {
    private String id;
    private String title;
    private String info;
    private String available;
    private String remark;
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

    public VoucherDetails[] getVoucherDetails() {
        return voucherDetails;
    }

    public void setVoucherDetails(VoucherDetails[] voucherDetails) {
        this.voucherDetails = voucherDetails;
    }



}
