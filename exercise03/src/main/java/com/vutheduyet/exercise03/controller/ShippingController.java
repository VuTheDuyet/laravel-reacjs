package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.Shipping;
import com.vutheduyet.exercise03.service.ShippingService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/shippings")
public class ShippingController {

    private ShippingService shippingService;

    // Create Shipping REST API
    @PostMapping
    public ResponseEntity<Shipping> createShipping(@RequestBody Shipping shipping) {
        Shipping savedShipping = shippingService.createShipping(shipping);
        return new ResponseEntity<>(savedShipping, HttpStatus.CREATED);
    }

    // Get Shipping by id REST API
    // http://localhost:8080/api/shippings/{id}
    @GetMapping("{id}")
    public ResponseEntity<Shipping> getShippingById(@PathVariable("id") Integer shippingId) {
        Shipping shipping = shippingService.getShippingById(shippingId);
        return new ResponseEntity<>(shipping, HttpStatus.OK);
    }

    // Get All Shippings REST API
    // http://localhost:8080/api/shippings
    @GetMapping
    public ResponseEntity<List<Shipping>> getAllShippings() {
        List<Shipping> shippings = shippingService.getAllShippings();
        return new ResponseEntity<>(shippings, HttpStatus.OK);
    }

    // Update Shipping REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/shippings/{id}
    public ResponseEntity<Shipping> updateShipping(@PathVariable("id") Integer shippingId,
                                                    @RequestBody Shipping shipping) {
        shipping.setShippingId(shippingId);
        Shipping updatedShipping = shippingService.updateShipping(shipping);
        if (updatedShipping != null) {
            return new ResponseEntity<>(updatedShipping, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Shipping REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteShipping(@PathVariable("id") Integer shippingId) {
        shippingService.deleteShipping(shippingId);
        return new ResponseEntity<>("Shipping successfully deleted!", HttpStatus.OK);
    }
}
