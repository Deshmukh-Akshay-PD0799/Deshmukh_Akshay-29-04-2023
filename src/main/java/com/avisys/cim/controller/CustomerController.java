package com.avisys.cim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.cim.payload.CustomerDto;
import com.avisys.cim.service.CustomerService;


@RestController
@RequestMapping("/api/customers")      
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	// get all customer
	@GetMapping 
	public ResponseEntity<List<CustomerDto>> getAllResources(){
			
		List<CustomerDto> allCustomers = this.customerService.getAllCustomers();
		return new ResponseEntity<List<CustomerDto>>(allCustomers, HttpStatus.OK);
	} 
	
	//get customer by mobile number
	@GetMapping("/{mobileNo}")
	public ResponseEntity<CustomerDto> getUser(@PathVariable String mobileNo) {   
		CustomerDto customerDto = this.customerService.getCustomer(mobileNo);
		return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
	}
	
	//search customer by firstName
	@GetMapping("/search/firstName/{keywords}")
	public ResponseEntity<List<CustomerDto>> searchFirstName(@PathVariable ("keywords") String keywords)
	{
		List<CustomerDto> result=this.customerService.searchFirstName(keywords);
		return new ResponseEntity<List<CustomerDto>>(result, HttpStatus.OK);
		
	}
	
	//search customer by lastname
	@GetMapping("/search/lastName/{keywords}")
	public ResponseEntity<List<CustomerDto>> searchLastName(@PathVariable ("keywords") String keywords)
	{
		List<CustomerDto> result=this.customerService.searchLastName(keywords);
		return new ResponseEntity<List<CustomerDto>>(result, HttpStatus.OK);
		
	}
	
	//search customer by first name or last name
	@GetMapping("/search/Name/{keywords}")
	public ResponseEntity<List<CustomerDto>> searchName(@PathVariable ("keywords") String keywords)
	{
		List<CustomerDto> result=this.customerService.searchName(keywords);
		return new ResponseEntity<List<CustomerDto>>(result, HttpStatus.OK);
		
	}
	
//	@PostMapping("/")
//	public ResponseEntity<CustomerDto> createDoctorN( @RequestBody CustomerDto cDto) {
//		CustomerDto create = this.customerService.createCustomer(cDto);
//		return new ResponseEntity<CustomerDto>(create, HttpStatus.CREATED);
//	}
	
	@PostMapping("/")
	public ResponseEntity<CustomerDto> createUser(@RequestBody CustomerDto cDto) throws com.avisys.cim.exceptions.DuplicateMobileNumberException{
		CustomerDto customerDto=this.customerService.createCustomer(cDto);
		return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.CREATED);
	}
	
}
