package com.tiffyn.service;

import com.tiffyn.exception.VendorAlreadyExistsException;
import com.tiffyn.exception.VendorNotFoundException;
import com.tiffyn.model.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorServiceTest {

    private VendorService vendorService;

    @BeforeEach
    void setUp() {
        vendorService = new VendorService();
    }

    @Test
    void shouldAddVendorSuccessfully() {

        Vendor vendor = new Vendor(
                "V001",
                "Neha",
                "9876543211",
                "neha@example.com",
                "Maa's Kitchen",
                "Greater Noida"
        );

        vendorService.addVendor(vendor);

        Vendor result =
                vendorService.getVendorById("V001");

        assertEquals("V001", result.getVendorId());
        assertEquals("Neha", result.getName());
        assertEquals("Maa's Kitchen", result.getBusinessName());
    }

    @Test
    void shouldRejectDuplicateVendor() {

        Vendor vendor = new Vendor(
                "V001",
                "Neha",
                "9876543211",
                "neha@example.com",
                "Maa's Kitchen",
                "Greater Noida"
        );

        vendorService.addVendor(vendor);

        assertThrows(
                VendorAlreadyExistsException.class,
                () -> vendorService.addVendor(vendor)
        );
    }

    @Test
    void shouldThrowExceptionWhenVendorDoesNotExist() {

        assertThrows(
                VendorNotFoundException.class,
                () -> vendorService.getVendorById("V999")
        );
    }

    @Test
    void shouldUpdateVendor() {

        Vendor vendor = new Vendor(
                "V001",
                "Neha",
                "9876543211",
                "neha@example.com",
                "Maa's Kitchen",
                "Greater Noida"
        );

        vendorService.addVendor(vendor);

        vendor.setBusinessName("Neha's Premium Kitchen");
        vendor.setAddress("Delhi");

        vendorService.updateVendor(vendor);

        Vendor updated =
                vendorService.getVendorById("V001");

        assertEquals(
                "Neha's Premium Kitchen",
                updated.getBusinessName()
        );

        assertEquals(
                "Delhi",
                updated.getAddress()
        );
    }

    @Test
    void shouldRemoveVendor() {

        Vendor vendor = new Vendor(
                "V001",
                "Neha",
                "9876543211",
                "neha@example.com",
                "Maa's Kitchen",
                "Greater Noida"
        );

        vendorService.addVendor(vendor);

        vendorService.removeVendor("V001");

        assertThrows(
                VendorNotFoundException.class,
                () -> vendorService.getVendorById("V001")
        );
    }
}
