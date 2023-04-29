package com.avisys.cim.service;

import java.util.List;

import com.avisys.cim.payload.CustomerDto;

public interface CustomerService {
	List<CustomerDto> getAllCustomers();
	
	CustomerDto getCustomer(String mobNo);
	
	
	List<CustomerDto> searchFirstName(String keyword);
	
	List<CustomerDto> searchLastName(String keyword);
	
	List<CustomerDto> searchName(String keyword);
	
	
	
//	List<CustomerDto> searchFirstName(String keyword);
	//List<Customer>
}
