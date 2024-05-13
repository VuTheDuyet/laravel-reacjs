package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.VariantOption;
import com.vutheduyet.exercise03.service.VariantOptionService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/variantOptions")
public class VariantOptionController {

    private VariantOptionService variantOptionService;

    // Create VariantOption REST API
    @PostMapping
    public ResponseEntity<VariantOption> createVariantOption(@RequestBody VariantOption variantOption) {
        VariantOption savedVariantOption = variantOptionService.createVariantOption(variantOption);
        return new ResponseEntity<>(savedVariantOption, HttpStatus.CREATED);
    }

    // Get VariantOption by id REST API
    // http://localhost:8080/api/variantOptions/{id}
    @GetMapping("{id}")
    public ResponseEntity<VariantOption> getVariantOptionById(@PathVariable("id") String variantOptionId) {
        VariantOption variantOption = variantOptionService.getVariantOptionById(variantOptionId);
        return new ResponseEntity<>(variantOption, HttpStatus.OK);
    }

    // Get All VariantOptions REST API
    // http://localhost:8080/api/variantOptions
    @GetMapping
    public ResponseEntity<List<VariantOption>> getAllVariantOptions() {
        List<VariantOption> variantOptions = variantOptionService.getAllVariantOptions();
        return new ResponseEntity<>(variantOptions, HttpStatus.OK);
    }

    // Update VariantOption REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/variantOptions/{id}
    public ResponseEntity<VariantOption> updateVariantOption(@PathVariable("id") String variantOptionId,
                                                      @RequestBody VariantOption variantOption) {
        variantOption.setVariantOptionId(variantOptionId);
        VariantOption updatedVariantOption = variantOptionService.updateVariantOption(variantOption);
        if (updatedVariantOption != null) {
            return new ResponseEntity<>(updatedVariantOption, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete VariantOption REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVariantOption(@PathVariable("id") String variantOptionId) {
        variantOptionService.deleteVariantOption(variantOptionId);
        return new ResponseEntity<>("VariantOption successfully deleted!", HttpStatus.OK);
    }
}
