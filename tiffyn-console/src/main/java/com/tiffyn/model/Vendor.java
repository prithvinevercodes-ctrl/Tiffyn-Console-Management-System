package com.tiffyn.model;

public class Vendor {

    private String vendorId;
    private String name;
    private String phone;
    private String email;
    private String businessName;
    private String address;

    public Vendor(String vendorId, String name, String phone,
                  String email, String businessName, String address) {
        this.vendorId = vendorId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.businessName = businessName;
        this.address = address;
    }

    public String getVendorId() {
        return vendorId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "vendorId='" + vendorId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", businessName='" + businessName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}