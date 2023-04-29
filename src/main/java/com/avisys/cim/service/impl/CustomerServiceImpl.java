package com.avisys.cim.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.Customer;
import com.avisys.cim.payload.CustomerDto;
import com.avisys.cim.repository.CustomerRepo;
import com.avisys.cim.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerDto getCustomer(String mobileNo) {
		Customer customer = this.customerRepo.findByMobileNo(mobileNo);
		
		return this.modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	public List<CustomerDto> getAllCustomers() {
		List<Customer> customers = this.customerRepo.findAllCustomers();

		List<CustomerDto> customerDtos = customers.stream()
				.map((customer) -> this.modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());

		return customerDtos;
	}
	
	public List<CustomerDto> searchFirstName(String keyword){
		List<Customer> firstName= this.customerRepo.searchByFirstName("%"+keyword+"%");
		List<CustomerDto> first= firstName.stream().map((customer) -> this.modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
		return first;
		
	}
	
	public List<CustomerDto> searchLastName(String keyword){
		List<Customer> lastName= this.customerRepo.searchByLastName("%"+keyword+"%");
		List<CustomerDto> last= lastName.stream().map((customer) -> this.modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
		return last;
		
	}

	@Override
	public List<CustomerDto> searchName(String keyword) {
		
		List<Customer> Name= this.customerRepo.searchByFullName("%"+keyword+"%");
		List<CustomerDto> FullName= Name.stream().map((customer) -> this.modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
		
		return FullName  ;
	}
	
}
