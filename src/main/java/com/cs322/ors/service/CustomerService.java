package com.cs322.ors.service;

import com.cs322.ors.db.CustomerRepository;
import com.cs322.ors.model.Customer;
import com.cs322.ors.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void editCustomer(Customer customer, long id){
        Customer updatedCustomer = customerRepository.findById(id).orElseThrow();
        updatedCustomer.setBalance(customer.getBalance());
        updatedCustomer.setEmail(customer.getEmail());
        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(updatedCustomer);
    }

    public void deleteCustomer(Customer customer, long id) {
        Customer deletedCustomer = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(deletedCustomer);
    }

}
