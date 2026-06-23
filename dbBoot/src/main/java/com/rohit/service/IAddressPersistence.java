package com.rohit.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohit.entities.address;

public interface IAddressPersistence extends JpaRepository<address, Long>{
	
}
