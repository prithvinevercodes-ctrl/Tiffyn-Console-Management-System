package com.tiffyn.service;

import com.tiffyn.model.Vendor;

import java.util.ArrayList;
import java.util.List;

public class VendorService {

    private final List<Vendor> vendors;

    public VendorService() {
        vendors = new ArrayList<>();
    }

    public void addVendor(Vendor vendor) {
        vendors.add(vendor);
    }

    public Vendor findVendorById(String vendorId) {

        for (Vendor vendor : vendors) {
            if (vendor.getVendorId().equals(vendorId)) {
                return vendor;
            }
        }

        return null;
    }

    public List<Vendor> getAllVendors() {
        return new ArrayList<>(vendors);
    }

    public void updateVendor(Vendor vendor) {

        Vendor existingVendor =
                findVendorById(vendor.getVendorId());

        if (existingVendor != null) {
            existingVendor.setName(vendor.getName());
            existingVendor.setPhone(vendor.getPhone());
            existingVendor.setEmail(vendor.getEmail());
            existingVendor.setBusinessName(vendor.getBusinessName());
            existingVendor.setAddress(vendor.getAddress());
        }
    }

    public void removeVendor(String vendorId) {

        vendors.removeIf(
                vendor -> vendor.getVendorId().equals(vendorId)
        );
    }
}