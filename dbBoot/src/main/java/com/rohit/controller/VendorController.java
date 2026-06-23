package com.rohit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rohit.entities.Vendor;

@RestController
public class VendorController {
	
	@Autowired
	com.rohit.service.vendorService vendorService;
	
	// READ - all vendors
	@RequestMapping("/vendor")
	public List<Vendor> getVendors(){
		return (List<Vendor>) vendorService.readAllVendors();
	}
	
	// READ - single vendor by id
	@GetMapping("/vendor/{id}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable("id") Long id) {
		Vendor result = vendorService.getSingleVendorById(id);
		if (result == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(result);
	}
	
	// CREATE
	@PostMapping("/vendor")
	public Vendor createVendor(@RequestBody Vendor myPostBody) {
		return vendorService.createVendor(myPostBody);
	}
	
	// UPDATE
	@PutMapping("/vendor/{id}")
	public ResponseEntity<Vendor> updateVendor(@PathVariable("id") Long id, @RequestBody Vendor vendor) {
		Vendor updated = vendorService.updateVendor(id, vendor);
		if (updated == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updated);
	}
	
	// DELETE
	@DeleteMapping("/vendor/{id}")
	public ResponseEntity<Void> deleteVendor(@PathVariable("id") Long id) {
		boolean deleted = vendorService.deleteVendor(id);
		if (!deleted) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	// SEARCH
	@RequestMapping("/vendor/search")
	public List<Vendor> searchByCompany(@RequestParam String company){
		return vendorService.searchByCompanyName(company);
	}
	
}