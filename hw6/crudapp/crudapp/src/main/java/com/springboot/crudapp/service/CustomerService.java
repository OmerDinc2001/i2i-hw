package com.springboot.crudapp.service;

import com.springboot.crudapp.mapper.CustomerMapper;
import com.springboot.crudapp.model.Customer;
import com.springboot.crudapp.model.CustomerDTO;
import com.springboot.crudapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerDTO> findAll() {
        return CustomerMapper.fromDomain(customerRepository.findAll());
    }

    public void save(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toDomain(customerDTO);
        customerRepository.save(customer);
    }

    public ResponseEntity<String> deleteById(Long id) {
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return new ResponseEntity<>("Customer deleted.", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Customer not found.", HttpStatus.NOT_FOUND);
        }
    }

    public CustomerDTO findById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::fromDomain)
                .orElse(null);
    }

    public void save(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer != null) {
            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());
            customerRepository.save(customer);
        }
    }
}
