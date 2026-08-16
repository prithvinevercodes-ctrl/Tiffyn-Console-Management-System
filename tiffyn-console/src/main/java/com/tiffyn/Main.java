package com.tiffyn;

import com.tiffyn.model.Vendor;
import com.tiffyn.service.VendorService;

public class Main {

    public static void main(String[] args) {

        VendorService vendorService = new VendorService();

        Vendor vendor1 = new Vendor(
                "V001",
                "Neha",
                "9876543211",
                "neha@example.com",
                "Maa's Kitchen",
                "Greater Noida"
        );

        Vendor vendor2 = new Vendor(
                "V002",
                "Amit",
                "9876543212",
                "amit@example.com",
                "Ghar Ka Tiffin",
                "Delhi"
        );

        // Add vendors
        vendorService.addVendor(vendor1);
        vendorService.addVendor(vendor2);

        System.out.println("All Vendors:");
        vendorService.getAllVendors()
                .forEach(System.out::println);

        // Find vendor
        System.out.println("\nSearching for V001:");
        System.out.println(
                vendorService.findVendorById("V001")
        );

        // Update vendor
        vendor1.setBusinessName("Neha's Premium Kitchen");
        vendorService.updateVendor(vendor1);

        System.out.println("\nAfter Update:");
        System.out.println(
                vendorService.findVendorById("V001")
        );

        // Remove vendor
        vendorService.removeVendor("V002");

        System.out.println("\nAfter Removing V002:");
        vendorService.getAllVendors()
                .forEach(System.out::println);
    }
}