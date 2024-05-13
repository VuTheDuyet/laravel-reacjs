package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Country;
import com.vutheduyet.exercise03.repository.CountryRepository;
import com.vutheduyet.exercise03.service.CountryService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    @Override
    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country getCountryById(Integer countryId) {
        Optional<Country> optionalCountry = countryRepository.findById(countryId);
        return optionalCountry.orElse(null);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country updateCountry(Country country) {
        Optional<Country> optionalExistingCountry = countryRepository.findById(country.getD());
        if (optionalExistingCountry.isPresent()) {
            Country existingCountry = optionalExistingCountry.get();
            existingCountry.setIso(country.getIso());
            existingCountry.setName(country.getName());
            existingCountry.setUpperName(country.getUpperName());
            existingCountry.setIso3(country.getIso3());
            existingCountry.setNumCode(country.getNumCode());
            existingCountry.setPhoneCode(country.getPhoneCode());

            Country updatedCountry = countryRepository.save(existingCountry);
            return updatedCountry;
        }

        return null;
    }

    @Override
    public void deleteCountry(Integer countryId) {
        countryRepository.deleteById(countryId);
    }
}
