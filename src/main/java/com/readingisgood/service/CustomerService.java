package com.readingisgood.service;

import com.readingisgood.dto.request.CreateCustomerRequestDTO;
import com.readingisgood.dto.response.MessageResponse;
import com.readingisgood.entity.Customer;
import com.readingisgood.mapper.IAddressMapper;
import com.readingisgood.repository.ICustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {

    private static final String MOBILE_APP = "MOBILEAPP";

    @Autowired
    private PasswordEncoder encoder;
    private final ICustomerRepository customerRepository;
    private final IAddressMapper addressMapper;
    private static final Logger logger = LogManager.getLogger(CustomerService.class);

    public CustomerService(ICustomerRepository customerRepository, IAddressMapper addressMapper)
    {
        this.customerRepository = customerRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<Customer> user = customerRepository.findByUsername(username);
        if (!user.isPresent())
        {
            logger.error("user is not found");
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }

    public ResponseEntity<?> createCustomer(CreateCustomerRequestDTO requestDTO)
    {
        if (customerRepository.existsByUsername(requestDTO.getUsername()))
        {
            logger.error("Username is already taken");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        else if (customerRepository.existsByEmail(requestDTO.getEmail()))
        {
            logger.error("Email is already in use");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        Customer customer = prepareCustomerEntity(requestDTO);
        customerRepository.save(customer);
        logger.info("User registered successfully!");
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private Customer prepareCustomerEntity(CreateCustomerRequestDTO requestDTO)
    {
        String passwordEncoded = encoder.encode(requestDTO.getPassword());

        Customer customer = new Customer();
        customer.setAddress(addressMapper.toAddress(requestDTO.getAddress()));
        customer.setEmail(requestDTO.getEmail());
        customer.setLastUpdateDate(LocalDateTime.now());
        customer.setName(requestDTO.getName());
        customer.setSurname(requestDTO.getSurname());
        customer.setUsername(requestDTO.getUsername());
        customer.setPassword(passwordEncoded);
        customer.setLastUpdatedUser(MOBILE_APP);
        customer.setPhone(requestDTO.getPhone());
        customer.setRecordDate(LocalDateTime.now());
        customer.setStatus('A');

        return customer;
    }
}
