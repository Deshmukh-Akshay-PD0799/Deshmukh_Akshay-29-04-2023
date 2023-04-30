package com.avisys.cim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avisys.cim.Customer;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{
	@Query("Select c from Customer c")
	public List<Customer> findAllCustomers();

	@Query("SELECT c FROM Customer c WHERE c.mobileNumber = :mobNo")
	public Customer findByMobileNo(@Param("mobNo") String mobileNo);

	@Query("SELECT u FROM Customer u WHERE LOWER(u.firstName) LIKE Lower(:key)")
	List<Customer> searchByFirstName(@Param("key") String firstName );
	
	@Query("SELECT u FROM Customer u WHERE LOWER(u.lastName) LIKE Lower(:key) ")
	List<Customer> searchByLastName(@Param("key") String lastName );
	
	@Query("SELECT u FROM Customer u WHERE LOWER(u.lastName) LIKE Lower(:key1) OR LOWER(u.firstName) LIKE Lower(:key1) ")
	List<Customer> searchByFullName(@Param("key1") String Name );
	
	@Modifying
	@Query("INSERT INTO Customer (id,firstName, lastName, mobileNumber) VALUES (:id,:firstName, :lastName, :mobileNumber)")
	void createCustomer(@Param("id") long id,@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("mobileNumber") String mobileNumber);
	
	@Query("SELECT c FROM Customer c WHERE c.id = :id")
	Customer findByCustomerId(@Param("id") Long id);
	
	// void deleteById(Customer customer);
}