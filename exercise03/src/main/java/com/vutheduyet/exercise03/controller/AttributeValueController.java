package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.AttributeValue;
import com.vutheduyet.exercise03.service.AttributeValueService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/attributeValues")
public class AttributeValueController {

    private AttributeValueService attributeValueService;

    // Create AttributeValue REST API
    @PostMapping
    public ResponseEntity<AttributeValue> createAttributeValue(@RequestBody AttributeValue attributeValue) {
        AttributeValue savedAttributeValue = attributeValueService.createAttributeValue(attributeValue);
        return new ResponseEntity<>(savedAttributeValue, HttpStatus.CREATED);
    }

    // Get AttributeValue by id REST API
    // http://localhost:8080/api/attributeValues/{id}
    @GetMapping("{id}")
    public ResponseEntity<AttributeValue> getAttributeValueById(@PathVariable("id") String attributeValueId) {
        AttributeValue attributeValue = attributeValueService.getAttributeValueById(attributeValueId);
        return new ResponseEntity<>(attributeValue, HttpStatus.OK);
    }

    // Get All AttributeValues REST API
    // http://localhost:8080/api/attributeValues
    @GetMapping
    public ResponseEntity<List<AttributeValue>> getAllAttributeValues() {
        List<AttributeValue> attributeValues = attributeValueService.getAllAttributeValues();
        return new ResponseEntity<>(attributeValues, HttpStatus.OK);
    }

    // Update AttributeValue REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/attributeValues/{id}
    public ResponseEntity<AttributeValue> updateAttributeValue(@PathVariable("id") String attributeValueId,
                                                                 @RequestBody AttributeValue attributeValue) {
        attributeValue.setAttributeValueId(attributeValueId);
        AttributeValue updatedAttributeValue = attributeValueService.updateAttributeValue(attributeValue);
        if (updatedAttributeValue != null) {
            return new ResponseEntity<>(updatedAttributeValue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete AttributeValue REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAttributeValue(@PathVariable("id") String attributeValueId) {
        attributeValueService.deleteAttributeValue(attributeValueId);
        return new ResponseEntity<>("AttributeValue successfully deleted!", HttpStatus.OK);
    }
}
