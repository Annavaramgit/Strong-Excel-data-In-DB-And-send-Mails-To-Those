package com.exceltodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exceltodb.enitity.Customer;

public interface Customer_Repository extends JpaRepository<Customer, Long> {

}
