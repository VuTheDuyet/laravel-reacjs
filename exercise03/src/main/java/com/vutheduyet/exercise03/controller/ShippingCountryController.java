package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.ShippingCountry;
import com.vutheduyet.exercise03.service.ShippingCountryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/shipping-countries")
public class ShippingCountryController {

    private ShippingCountryService shippingCountryService;

    // Create ShippingCountry REST API
    @PostMapping
    public ResponseEntity<ShippingCountry> createShippingCountry(@RequestBody ShippingCountry shippingCountry) {
        ShippingCountry savedShippingCountry = shippingCountryService.createShippingCountry(shippingCountry);
        return new ResponseEntity<>(savedShippingCountry, HttpStatus.CREATED);
    }

    // Get ShippingCountry by id REST API
    // http://localhost:8080/api/shipping-countries/{id}
    @GetMapping("{id}")
    public ResponseEntity<ShippingCountry> getShippingCountryById(@PathVariable("id") String shippingCountryId) {
        ShippingCountry shippingCountry = shippingCountryService.getShippingCountryById(shippingCountryId);
        return new ResponseEntity<>(shippingCountry, HttpStatus.OK);
    }

    // Get All ShippingCountrys REST API
    // http://localhost:8080/api/shipping-countries
    @GetMapping
    public ResponseEntity<List<ShippingCountry>> getAllShippingCountrys() {
        List<ShippingCountry> shippingCountries = shippingCountryService.getAllShippingCountrys();
        return new ResponseEntity<>(shippingCountries, HttpStatus.OK);
    }

    // Update ShippingCountry REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/shipping-countries/{id}
    public ResponseEntity<ShippingCountry> updateShippingCountry(@PathVariable("id") String shippingCountryId,
                                            @RequestBody ShippingCountry shippingCountry) {
        shippingCountry.setShippingCountryId(shippingCountryId);
        ShippingCountry updatedShippingCountry = shippingCountryService.updateShippingCountry(shippingCountry);
        if (updatedShippingCountry != null) {
            return new ResponseEntity<>(updatedShippingCountry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete ShippingCountry REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteShippingCountry(@PathVariable("id") String shippingCountryId) {
        shippingCountryService.deleteShippingCountry(shippingCountryId);
        return new ResponseEntity<>("ShippingCountry successfully deleted!", HttpStatus.OK);
    }
}
