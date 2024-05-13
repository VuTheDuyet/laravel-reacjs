package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.Country;
import com.vutheduyet.exercise03.service.CountryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/countries")
public class CountryController {

    private CountryService countryService;

    // Create Country REST API
    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country savedCountry = countryService.createCountry(country);
        return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
    }

    // Get Country by id REST API
    // http://localhost:8080/api/countries/{id}
    @GetMapping("{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable("id") Integer countryId) {
        Country country = countryService.getCountryById(countryId);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    // Get All Countries REST API
    // http://localhost:8080/api/countries
    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // Update Country REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/countries/{id}
    public ResponseEntity<Country> updateCountry(@PathVariable("id") Integer countryId,
                                                  @RequestBody Country country) {
        country.setD(countryId);
        Country updatedCountry = countryService.updateCountry(country);
        if (updatedCountry != null) {
            return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Country REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable("id") Integer countryId) {
        countryService.deleteCountry(countryId);
        return new ResponseEntity<>("Country successfully deleted!", HttpStatus.OK);
    }
}
