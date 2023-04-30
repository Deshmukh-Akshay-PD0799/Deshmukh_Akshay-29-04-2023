package com.avisys.cim.service;

import java.util.List;

import com.avisys.cim.payload.CustomerDto;

// Services which provided to the client
public interface CustomerService {
	List<CustomerDto> getAllCustomers();
	
	CustomerDto getCustomer(String mobNo);
	
	List<CustomerDto> searchFirstName(String keyword);
	
	List<CustomerDto> searchLastName(String keyword);
	
	List<CustomerDto> searchName(String keyword);

	CustomerDto createCustomer(CustomerDto cDto) throws com.avisys.cim.exceptions.DuplicateMobileNumberException;

	CustomerDto addMobileNumber(Long id, String mobileNumber) throws com.avisys.cim.exceptions.DuplicateMobileNumberException;

	void deleteCustomer(String mobileNumber);

	void deleteMobileNo(long id, String mobileNumber);
	
	
}
