package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Attribute;
import com.vutheduyet.exercise03.repository.AttributeRepository;
import com.vutheduyet.exercise03.service.AttributeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttributeServiceImpl implements AttributeService {
    private AttributeRepository attributeRepository;

    @Override
    public Attribute createAttribute(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    @Override
    public Attribute getAttributeById(String attributeId) {
        Optional<Attribute> optionalAttribute = attributeRepository.findById(attributeId);
        return optionalAttribute.orElse(null);
    }

    @Override
    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }

    @Override
    public Attribute updateAttribute(Attribute attribute) {
        Optional<Attribute> optionalExistingAttribute = attributeRepository.findById(attribute.getAttributeId());
        if (optionalExistingAttribute.isPresent()) {
            Attribute existingAttribute = optionalExistingAttribute.get();
            existingAttribute.setAttributeName(attribute.getAttributeName());
            existingAttribute.setUpdatedAt(LocalDateTime.now());
            existingAttribute.setUpdatedBy(attribute.getUpdatedBy());
    
            Attribute updatedAttribute = attributeRepository.save(existingAttribute);
            return updatedAttribute;
        }
    
        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy thuộc tính tồn tại
    }
    

    @Override
    public void deleteAttribute(String attributeId) {
        attributeRepository.deleteById(attributeId);
    }
}
