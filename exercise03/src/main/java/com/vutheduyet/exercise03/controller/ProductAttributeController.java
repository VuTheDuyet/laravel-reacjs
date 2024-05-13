package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.ProductAttribute;
import com.vutheduyet.exercise03.service.ProductAttributeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/productAttributes")
public class ProductAttributeController {

    private ProductAttributeService productAttributeService;

    // Create ProductAttribute REST API
    @PostMapping
    public ResponseEntity<ProductAttribute> createProductAttribute(@RequestBody ProductAttribute productAttribute) {
        ProductAttribute savedProductAttribute = productAttributeService.createProductAttribute(productAttribute);
        return new ResponseEntity<>(savedProductAttribute, HttpStatus.CREATED);
    }

    // Get ProductAttribute by id REST API
    // http://localhost:8080/api/productAttributes/{id}
    @GetMapping("{id}")
    public ResponseEntity<ProductAttribute> getProductAttributeById(@PathVariable("id") String productAttributeId) {
        ProductAttribute productAttribute = productAttributeService.getProductAttributeById(productAttributeId);
        return new ResponseEntity<>(productAttribute, HttpStatus.OK);
    }

    // Get All ProductAttributes REST API
    // http://localhost:8080/api/productAttributes
    @GetMapping
    public ResponseEntity<List<ProductAttribute>> getAllProductAttributes() {
        List<ProductAttribute> productAttributes = productAttributeService.getAllProductAttributes();
        return new ResponseEntity<>(productAttributes, HttpStatus.OK);
    }

    // Update ProductAttribute REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/productAttributes/{id}
    public ResponseEntity<ProductAttribute> updateProductAttribute(@PathVariable("id") String productAttributeId,
                                                                    @RequestBody ProductAttribute productAttribute) {
        productAttribute.setProductAttributeId(productAttributeId);
        ProductAttribute updatedProductAttribute = productAttributeService.updateProductAttribute(productAttribute);
        if (updatedProductAttribute != null) {
            return new ResponseEntity<>(updatedProductAttribute, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete ProductAttribute REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductAttribute(@PathVariable("id") String productAttributeId) {
        productAttributeService.deleteProductAttribute(productAttributeId);
        return new ResponseEntity<>("ProductAttribute successfully deleted!", HttpStatus.OK);
    }
}
