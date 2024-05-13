package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.ProductSupply;
import com.vutheduyet.exercise03.service.ProductSupplyService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/product-supplies")
public class ProductSupplyController {

    private ProductSupplyService productSupplyService;

    // Create ProductSupply REST API
    @PostMapping
    public ResponseEntity<ProductSupply> createProductSupply(@RequestBody ProductSupply productSupply) {
        ProductSupply savedProductSupply = productSupplyService.createProductSupply(productSupply);
        return new ResponseEntity<>(savedProductSupply, HttpStatus.CREATED);
    }

    // Get ProductSupply by id REST API
    // http://localhost:8080/api/product-supplies/{id}
    @GetMapping("{id}")
    public ResponseEntity<ProductSupply> getProductSupplyById(@PathVariable("id") String productSupplyId) {
        ProductSupply productSupply = productSupplyService.getProductSupplyById(productSupplyId);
        return new ResponseEntity<>(productSupply, HttpStatus.OK);
    }

    // Get All ProductSupplies REST API
    // http://localhost:8080/api/product-supplies
    @GetMapping
    public ResponseEntity<List<ProductSupply>> getAllProductSupplies() {
        List<ProductSupply> productSupplies = productSupplyService.getAllProductSupplys();
        return new ResponseEntity<>(productSupplies, HttpStatus.OK);
    }

    // Update ProductSupply REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/product-supplies/{id}
    public ResponseEntity<ProductSupply> updateProductSupply(@PathVariable("id") String productSupplyId,
                                                  @RequestBody ProductSupply productSupply) {
        productSupply.setProductSupplyId(productSupplyId);
        ProductSupply updatedProductSupply = productSupplyService.updateProductSupply(productSupply);
        if (updatedProductSupply != null) {
            return new ResponseEntity<>(updatedProductSupply, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete ProductSupply REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductSupply(@PathVariable("id") String productSupplyId) {
        productSupplyService.deleteProductSupply(productSupplyId);
        return new ResponseEntity<>("ProductSupply successfully deleted!", HttpStatus.OK);
    }
}
