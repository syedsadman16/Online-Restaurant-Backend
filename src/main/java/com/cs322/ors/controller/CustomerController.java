package com.cs322.ors.controller;

import com.cs322.ors.model.Customer;
import com.cs322.ors.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @RequestMapping(method=RequestMethod.POST, value="/addCustomer")
    public void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/edit/{id}")
    public void editCustomer(@RequestBody Customer customer, @PathVariable long id){
        customerService.editCustomer(customer, id);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/delete/{id}")
    public void deleteCustomer(@RequestBody Customer customer, @PathVariable long id){
        customerService.deleteCustomer(customer, id);
    }

}
