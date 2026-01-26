package com.hooeats.api.entity;

public class Vendor {
    private String vendorId;
    private String companyName;
    private String repFname;
    private String repLname;
    private String referredBy;

    public Vendor() {}

    public Vendor(String vendorId, String companyName, String repFname, String repLname, String referredBy) {
        this.vendorId = vendorId;
        this.companyName = companyName;
        this.repFname = repFname;
        this.repLname = repLname;
        this.referredBy = referredBy;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRepFname() {
        return repFname;
    }

    public void setRepFname(String repFname) {
        this.repFname = repFname;
    }

    public String getRepLname() {
        return repLname;
    }

    public void setRepLname(String repLname) {
        this.repLname = repLname;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }
}
