package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.CustomerAddress;
import com.vutheduyet.exercise03.repository.CustomerAddressRepository;
import com.vutheduyet.exercise03.service.CustomerAddressService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerAddressServiceImpl implements CustomerAddressService {
    private CustomerAddressRepository customerAddressRepository;

    @Override
    public CustomerAddress createCustomerAddress(CustomerAddress customerAddress) {
        return customerAddressRepository.save(customerAddress);
    }

    @Override
    public CustomerAddress getCustomerAddressById(String customerAddressId) {
        Optional<CustomerAddress> optionalCustomerAddress = customerAddressRepository.findById(customerAddressId);
        return optionalCustomerAddress.orElse(null);
    }

    @Override
    public List<CustomerAddress> getAllCustomerAddresses() {
        return customerAddressRepository.findAll();
    }

    @Override
    public CustomerAddress updateCustomerAddress(CustomerAddress customerAddress) {
        Optional<CustomerAddress> optionalExistingCustomerAddress = customerAddressRepository.findById(customerAddress.getCustomerAddressId());
        if (optionalExistingCustomerAddress.isPresent()) {
            CustomerAddress existingCustomerAddress = optionalExistingCustomerAddress.get();
            existingCustomerAddress.setAddressLine1(customerAddress.getAddressLine1());
            existingCustomerAddress.setAddressLine2(customerAddress.getAddressLine2());
            existingCustomerAddress.setPhoneNumber(customerAddress.getPhoneNumber());
            existingCustomerAddress.setDialCode(customerAddress.getDialCode());
            existingCustomerAddress.setCountry(customerAddress.getCountry());
            existingCustomerAddress.setPostalCode(customerAddress.getPostalCode());
            existingCustomerAddress.setCity(customerAddress.getCity());

            CustomerAddress updatedCustomerAddress = customerAddressRepository.save(existingCustomerAddress);
            return updatedCustomerAddress;
        }

        return null;
    }

    @Override
    public void deleteCustomerAddress(String customerAddressId) {
        customerAddressRepository.deleteById(customerAddressId);
    }
}
