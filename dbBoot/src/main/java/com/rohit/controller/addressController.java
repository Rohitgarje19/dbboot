package com.rohit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rohit.entities.address;
import com.rohit.service.addressService;

@RestController
public class addressController {
	
	@Autowired 
	addressService addrSrv;
	
	@RequestMapping("/addresses")
	public List<address> getAddress(){
		return addrSrv.getAddress();
	}
	
	@PostMapping("/addresses")
	public address createAddress(@RequestBody address payload) {
		return addrSrv.createAddress(payload);
	}
}
