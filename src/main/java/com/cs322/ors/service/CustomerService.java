package com.cs322.ors.service;

import com.cs322.ors.db.CustomerRepository;
import com.cs322.ors.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void editCustomer(Customer customer, String id){
        Customer updatedCustomer = customerRepository.findById(id).orElseThrow();
        updatedCustomer = customer;
        customerRepository.save(updatedCustomer);
    }

    public void deleteCustomer(Customer customer, String id) {
        Customer deletedCustomer = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(deletedCustomer);
    }

}
