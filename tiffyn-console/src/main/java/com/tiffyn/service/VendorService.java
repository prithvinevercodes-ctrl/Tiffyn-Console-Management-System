package com.tiffyn.service;

import com.tiffyn.exception.VendorAlreadyExistsException;
import com.tiffyn.exception.VendorNotFoundException;
import com.tiffyn.model.Vendor;

import java.util.ArrayList;
import java.util.List;

public class VendorService {

    private final List<Vendor> vendors;

    public VendorService() {
        vendors = new ArrayList<>();
    }

    public void addVendor(Vendor vendor) {

        if (findVendorById(vendor.getVendorId()) != null) {
            throw new VendorAlreadyExistsException(
                    "Vendor with ID "
                            + vendor.getVendorId()
                            + " already exists."
            );
        }

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

    public Vendor getVendorById(String vendorId) {

        Vendor vendor = findVendorById(vendorId);

        if (vendor == null) {
            throw new VendorNotFoundException(
                    "Vendor with ID "
                            + vendorId
                            + " not found."
            );
        }

        return vendor;
    }

    public List<Vendor> getAllVendors() {
        return new ArrayList<>(vendors);
    }

    public void updateVendor(Vendor vendor) {

        Vendor existingVendor =
                getVendorById(vendor.getVendorId());

        existingVendor.setName(vendor.getName());
        existingVendor.setPhone(vendor.getPhone());
        existingVendor.setEmail(vendor.getEmail());
        existingVendor.setBusinessName(vendor.getBusinessName());
        existingVendor.setAddress(vendor.getAddress());
    }

    public void removeVendor(String vendorId) {

        Vendor vendor = getVendorById(vendorId);

        vendors.remove(vendor);
    }
}