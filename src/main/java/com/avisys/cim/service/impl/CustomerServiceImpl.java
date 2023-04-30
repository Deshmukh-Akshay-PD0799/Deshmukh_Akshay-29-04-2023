package com.avisys.cim.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.Customer;
import com.avisys.cim.exceptions.DuplicateMobileNumberException;
import com.avisys.cim.payload.CustomerDto;
import com.avisys.cim.repository.CustomerRepo;
import com.avisys.cim.service.CustomerService;

//Implementation of Buissness Logic

@Service
public class CustomerServiceImpl implements CustomerService {

	static long i = 5;

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

	public List<CustomerDto> searchFirstName(String keyword) {
		List<Customer> firstName = this.customerRepo.searchByFirstName("%" + keyword + "%");
		List<CustomerDto> first = firstName.stream()
				.map((customer) -> this.modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
		return first;

	}

	public List<CustomerDto> searchLastName(String keyword) {
		List<Customer> lastName = this.customerRepo.searchByLastName("%" + keyword + "%");
		List<CustomerDto> last = lastName.stream().map((customer) -> this.modelMapper.map(customer, CustomerDto.class))
				.collect(Collectors.toList());
		return last;

	}

	@Override
	public List<CustomerDto> searchName(String keyword) {

		List<Customer> Name = this.customerRepo.searchByFullName("%" + keyword + "%");
		List<CustomerDto> FullName = Name.stream().map((customer) -> this.modelMapper.map(customer, CustomerDto.class))
				.collect(Collectors.toList());

		return FullName;
	}

	@Override
	public CustomerDto createCustomer(CustomerDto cDto) throws DuplicateMobileNumberException {
		Customer customer = this.modelMapper.map(cDto, Customer.class);
		if (this.customerRepo.findByMobileNo(customer.getMobileNumber()) != null) {
			throw new DuplicateMobileNumberException("Unable to create Customer. Mobile number already present");
		}
		customer.setId(i);
		this.customerRepo.createCustomer(customer.getId(), customer.getFirstName(), customer.getLastName(),
				customer.getMobileNumber());
		i++;
		Customer addedCustomer = this.customerRepo.findByMobileNo(customer.getMobileNumber());
		return this.modelMapper.map(addedCustomer, CustomerDto.class);
	}

	@Override
	public CustomerDto addMobileNumber(Long id, String mobileNumber) throws DuplicateMobileNumberException {
		List<Customer> customers = this.customerRepo.findAllCustomers();

		for (Customer c : customers) {
			String[] m = c.getMobileNumber().split(",");
			for (String str : m) {
				if (str.equals(mobileNumber)) {
					throw new DuplicateMobileNumberException(
							"Unable to create Customer. Mobile number already present");
				}
			}
		}

		Customer customer = this.customerRepo.findByCustomerId(id);
		StringBuilder sb = new StringBuilder(customer.getMobileNumber());
		sb.append(",");
		sb.append(mobileNumber);
		customer.setMobileNumber(sb.toString());
		Customer updatedCustomer = customerRepo.save(customer);
		return this.modelMapper.map(updatedCustomer, CustomerDto.class);
	}

	// delete customer when customer has a mobileNumber
//	@Override
//	public void deleteCustomer(String mobileNumber) {
//			Customer customer=this.customerRepo.findByMobileNo(mobileNumber);
//			this.customerRepo.delete(customer);	
//		}
	
	// delete customer when customer has multiple mobileNumber
	@Override
	public void deleteCustomer(String mobileNumber) {
//			Customer customer=this.customerRepo.findByMobileNo(mobileNumber);
			List<Customer> customers = this.customerRepo.findAllCustomers();

			for (Customer c : customers) {
				String[] m = c.getMobileNumber().split(",");
				for (String str : m) {
					if (str.equals(mobileNumber)) {
						StringBuilder sb = new StringBuilder(str);
						this.customerRepo.delete(c);
					}
				}
			}
	
	}

	@Override
	public void deleteMobileNo(long id, String mobileNumber) {
	    Customer customer = this.customerRepo.findByCustomerId(id);
	    String[] m = customer.getMobileNumber().split(",");
	    StringBuilder sb = new StringBuilder(customer.getMobileNumber());
	    for (String str : m) {
	        if (str.equals(mobileNumber)) {
	            sb.delete(sb.indexOf(str), sb.indexOf(str) + str.length() + 1);
	            break;
	        }
	    }
	    customer.setMobileNumber(sb.toString());
	    this.customerRepo.save(customer);
	}
}
