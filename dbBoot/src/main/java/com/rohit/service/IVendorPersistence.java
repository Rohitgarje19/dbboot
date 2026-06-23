package com.rohit.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rohit.entities.Vendor;

public interface IVendorPersistence extends JpaRepository<Vendor, Long>{
	List<Vendor> findBycompanyName(String companyName);
	
	@Query(
		    nativeQuery = true,
		    value = "SELECT * FROM public.vendor WHERE LOWER(gst_no) LIKE LOWER(CONCAT('%', ?1, '%'))"
		)
		List<Vendor> lookupVendorByGST(String gstNo);
}
