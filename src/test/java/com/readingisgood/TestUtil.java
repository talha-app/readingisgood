package com.readingisgood;

import com.github.javafaker.Faker;
import com.readingisgood.dto.AddressDTO;
import com.readingisgood.dto.request.CreateCustomerRequestDTO;

import java.util.Locale;

public class TestUtil {
    public static final String TEST_USERNAME = "testUser";
    public static final String TEST_PASSWORD = "123456";
    public static final Faker faker = new Faker();

    public static CreateCustomerRequestDTO prepareCreateCustomerRequestDTO()
    {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress("testAddress");
        addressDTO.setCity("testCity");
        addressDTO.setCountry("testCountry");
        addressDTO.setPostalCode("12345");

        String name = faker.name().firstName().toLowerCase(Locale.ROOT);

        CreateCustomerRequestDTO createCustomerRequestDTO = new CreateCustomerRequestDTO();
        createCustomerRequestDTO.setUsername(TEST_USERNAME);
        createCustomerRequestDTO.setPassword(TEST_PASSWORD);
        createCustomerRequestDTO.setEmail(name+"@test.com");
        createCustomerRequestDTO.setName(name);
        createCustomerRequestDTO.setSurname("testSurname");
        createCustomerRequestDTO.setAddress(addressDTO);
        createCustomerRequestDTO.setPhone("12345");

        return createCustomerRequestDTO;
    }

}
