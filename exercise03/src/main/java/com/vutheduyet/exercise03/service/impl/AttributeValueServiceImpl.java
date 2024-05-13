package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.AttributeValue;
import com.vutheduyet.exercise03.repository.AttributeValueRepository;
import com.vutheduyet.exercise03.service.AttributeValueService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttributeValueServiceImpl implements AttributeValueService {
    private AttributeValueRepository attributeValueRepository;

    @Override
    public AttributeValue createAttributeValue(AttributeValue attributeValue) {
        return attributeValueRepository.save(attributeValue);
    }

    @Override
    public AttributeValue getAttributeValueById(String attributeValueId) {
        Optional<AttributeValue> optionalAttributeValue = attributeValueRepository.findById(attributeValueId);
        return optionalAttributeValue.orElse(null);
    }

    @Override
    public List<AttributeValue> getAllAttributeValues() {
        return attributeValueRepository.findAll();
    }

    @Override
    public AttributeValue updateAttributeValue(AttributeValue attributeValue) {
        Optional<AttributeValue> optionalExistingAttributeValue = attributeValueRepository.findById(attributeValue.getAttributeValueId());
        if (optionalExistingAttributeValue.isPresent()) {
            AttributeValue existingAttributeValue = optionalExistingAttributeValue.get();
            existingAttributeValue.setAttribute(attributeValue.getAttribute());
            existingAttributeValue.setAttributeValue(attributeValue.getAttributeValue());
            existingAttributeValue.setColor(attributeValue.getColor());

            AttributeValue updatedAttributeValue = attributeValueRepository.save(existingAttributeValue);
            return updatedAttributeValue;
        }

        return null;
    }

    @Override
    public void deleteAttributeValue(String attributeValueId) {
        attributeValueRepository.deleteById(attributeValueId);
    }
}
