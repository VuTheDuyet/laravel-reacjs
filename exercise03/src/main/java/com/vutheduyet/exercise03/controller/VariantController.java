package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.Variant;
import com.vutheduyet.exercise03.service.VariantService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/variants")
public class VariantController {

    private VariantService variantService;

    // Create Variant REST API
    @PostMapping
    public ResponseEntity<Variant> createVariant(@RequestBody Variant variant) {
        Variant savedVariant = variantService.createVariant(variant);
        return new ResponseEntity<>(savedVariant, HttpStatus.CREATED);
    }

    // Get Variant by id REST API
    // http://localhost:8080/api/variants/{id}
    @GetMapping("{id}")
    public ResponseEntity<Variant> getVariantById(@PathVariable("id") String variantId) {
        Variant variant = variantService.getVariantById(variantId);
        return new ResponseEntity<>(variant, HttpStatus.OK);
    }

    // Get All Variants REST API
    // http://localhost:8080/api/variants
    @GetMapping
    public ResponseEntity<List<Variant>> getAllVariants() {
        List<Variant> variants = variantService.getAllVariants();
        return new ResponseEntity<>(variants, HttpStatus.OK);
    }

    // Update Variant REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/variants/{id}
    public ResponseEntity<Variant> updateVariant(@PathVariable("id") String variantId,
                                                      @RequestBody Variant variant) {
        variant.setVariantId(variantId);
        Variant updatedVariant = variantService.updateVariant(variant);
        if (updatedVariant != null) {
            return new ResponseEntity<>(updatedVariant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Variant REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVariant(@PathVariable("id") String variantId) {
        variantService.deleteVariant(variantId);
        return new ResponseEntity<>("Variant successfully deleted!", HttpStatus.OK);
    }
}
