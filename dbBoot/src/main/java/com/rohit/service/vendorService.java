package com.rohit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rohit.entities.Vendor;

@Component
public class vendorService {
	@Autowired
	IVendorPersistence vendor;
	
	public List<Vendor> readAllVendors(){
		return vendor.findAll();
	}
	
	public Vendor getSingleVendorById(Long id) {
		Optional<Vendor> result = vendor.findById(id);
		return result.orElse(null);
	}
	
	public Vendor createVendor(Vendor vendorObj) {
		return vendor.save(vendorObj);
	}
	
	public Vendor updateVendor(Long id, Vendor vendorObj) {
		Optional<Vendor> existing = vendor.findById(id);
		if (existing.isEmpty()) {
			return null;
		}
		Vendor vendorToUpdate = existing.get();
		vendorToUpdate.setCompanyName(vendorObj.getCompanyName());
		vendorToUpdate.setFirstName(vendorObj.getFirstName());
		vendorToUpdate.setLastName(vendorObj.getLastName());
		vendorToUpdate.setWebsite(vendorObj.getWebsite());
		vendorToUpdate.setEmail(vendorObj.getEmail());
		vendorToUpdate.setStatus(vendorObj.getStatus());
		vendorToUpdate.setGstNo(vendorObj.getGstNo());
		return vendor.save(vendorToUpdate);
	}
	
	public boolean deleteVendor(Long id) {
		if (!vendor.existsById(id)) {
			return false;
		}
		vendor.deleteById(id);
		return true;
	}
	
	public List<Vendor> searchByCompanyName(String companyName){
		return vendor.findBycompanyName(companyName);
	}

}