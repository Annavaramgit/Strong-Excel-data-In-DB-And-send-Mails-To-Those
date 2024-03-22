package com.exceltodb.controller;

/*  --->this application is for save the all the details present in the excel
 *  --->simply it is importing excel data into data base by using spring boot
 * 
 */



import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exceltodb.enitity.Customer;
import com.exceltodb.service.CustomerService;

@RestController
public class Customer_Controller {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> savingMethod(@RequestParam("File") MultipartFile file){
		
		customerService.saveMethod(file);
		
		return ResponseEntity.ok(Map.of("messge::","saved successfully"));
		
	}
	@GetMapping("/fetching")
	public ResponseEntity<List<Customer>> fetchAllMehod(){
		List<Customer> customers = customerService.fetchAllMethod();
		return new ResponseEntity<>(customers,HttpStatus.OK);
		
	}
	
	@GetMapping("/fetching/{id}")
	public ResponseEntity<Customer> fetchParticularMethod(@PathVariable long id){
		Customer customer = customerService.fetchPartcularMethod(id);
		return new ResponseEntity<>(customer,HttpStatus.OK);
		
	}
}
