package com.springboot.crudapp.controller;


import com.springboot.crudapp.model.CustomerDTO;
import com.springboot.crudapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDTO> findAll() {
        return customerService.findAll();
    }

    @PostMapping("/create")
    public void save(@RequestBody CustomerDTO customerDTO) {
        customerService.save(customerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
    }

    @GetMapping("/find/{id}")
    public CustomerDTO findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PutMapping("/update/{id}")
    public void save(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        customerService.save(id, customerDTO);
    }
}
