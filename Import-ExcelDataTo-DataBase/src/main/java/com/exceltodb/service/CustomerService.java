package com.exceltodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exceltodb.enitity.Customer;
import com.exceltodb.helper.Helper;
import com.exceltodb.repository.Customer_Repository;

@Service
public class CustomerService {

	@Autowired
	private Customer_Repository customer_Repository;
	
	
	public void saveMethod(MultipartFile file)
	{
		if(Helper.checkExcelFormat(file)) {
		try {
		List<Customer>customers=Helper.covertExcelDataToCustomerData(file.getInputStream());
		customer_Repository.saveAll(customers);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			}
		}
	
	
	
	
	public List<Customer> fetchAllMethod(){
		return customer_Repository.findAll();
	}
	
	
	public Customer fetchPartcularMethod(long id) {
		return customer_Repository.findById(id).get();
	}
	
}
