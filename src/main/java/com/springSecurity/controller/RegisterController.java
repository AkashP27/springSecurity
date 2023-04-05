package com.springSecurity.controller;

import com.springSecurity.model.Customer;
import com.springSecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("mylogin")
    public String login() {
        return "Login page";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) {
        Customer savedCustomer = null;
        ResponseEntity response = null;

        try {
            String hashedPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashedPassword);
            savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("User registered successfully");
            }

        } catch(Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception occured due to :" + ex.getMessage());
        }

        return response;
    }
}
