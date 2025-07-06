package com.springboot.crudapp.mapper;

import com.springboot.crudapp.model.Customer;
import com.springboot.crudapp.model.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {
    public static Customer toDomain(CustomerDTO customerDTO) {
        if(customerDTO != null) {
            Customer customer = new Customer();
            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());
            return customer;
        }
        else return null;
    }

    public static CustomerDTO fromDomain(Customer customer) {
        if(customer != null) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setName(customer.getName());
            customerDTO.setEmail(customer.getEmail());
            return customerDTO;
        }
        else return null;
    }

    public static List<CustomerDTO> fromDomain(List<Customer> customers) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer i : customers) customerDTOS.add(fromDomain(i));
        return customerDTOS;
    }
}
