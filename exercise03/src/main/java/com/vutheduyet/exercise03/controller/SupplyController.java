package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.Supply;
import com.vutheduyet.exercise03.service.SupplyService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/supplies")
public class SupplyController {

    private SupplyService supplyService;

    // Create Supply REST API
    @PostMapping
    public ResponseEntity<Supply> createSupply(@RequestBody Supply supply) {
        Supply savedSupply = supplyService.createSupply(supply);
        return new ResponseEntity<>(savedSupply, HttpStatus.CREATED);
    }

    // Get Supply by id REST API
    // http://localhost:8080/api/supplies/{id}
    @GetMapping("{id}")
    public ResponseEntity<Supply> getSupplyById(@PathVariable("id") String supplyId) {
        Supply supply = supplyService.getSupplyById(supplyId);
        return new ResponseEntity<>(supply, HttpStatus.OK);
    }

    // Get All Supplies REST API
    // http://localhost:8080/api/supplies
    @GetMapping
    public ResponseEntity<List<Supply>> getAllSupplies() {
        List<Supply> supplies = supplyService.getAllSupplys();
        return new ResponseEntity<>(supplies, HttpStatus.OK);
    }

    // Update Supply REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/supplies/{id}
    public ResponseEntity<Supply> updateSupply(@PathVariable("id") String supplyId,
                                            @RequestBody Supply supply) {
        supply.setSupplyId(supplyId);
        Supply updatedSupply = supplyService.updateSupply(supply);
        if (updatedSupply != null) {
            return new ResponseEntity<>(updatedSupply, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Supply REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSupply(@PathVariable("id") String supplyId) {
        supplyService.deleteSupply(supplyId);
        return new ResponseEntity<>("Supply successfully deleted!", HttpStatus.OK);
    }
}
