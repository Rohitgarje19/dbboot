package com.rohit.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.*;

import com.rohit.entities.Vendor;


@RepositoryRestResource(collectionResourceRel="vendor",path="newVendor")
public interface vendorControllerNew extends JpaRepository<Vendor, Long> {
  
}
