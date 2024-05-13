package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.ProductTag;
import com.vutheduyet.exercise03.service.ProductTagService;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RestController
@AllArgsConstructor
@RequestMapping("api/productTags")
public class ProductTagController {

    private ProductTagService productTagService;

    // Create ProductTag REST API
    @PostMapping
    public ResponseEntity<ProductTag> createProductTag(@RequestBody ProductTag productTag) {
        ProductTag savedProductTag = productTagService.createProductTag(productTag);
        return new ResponseEntity<>(savedProductTag, HttpStatus.CREATED);
    }

    // Get ProductTag by id REST API
    // http://localhost:8080/api/productTags/{id}
    @GetMapping("{id}")
    public ResponseEntity<ProductTag> getProductTagById(@PathVariable("id") String productTagId) {
        ProductTag productTag = productTagService.getProductTagById(productTagId);
        return new ResponseEntity<>(productTag, HttpStatus.OK);
    }

    // Get All ProductTags REST API
    // http://localhost:8080/api/productTags
    @GetMapping
    public ResponseEntity<List<ProductTag>> getAllProductTags() {
        List<ProductTag> productTags = productTagService.getAllProductTags();
        return new ResponseEntity<>(productTags, HttpStatus.OK);
    }

    // Update ProductTag REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/productTags/{id}
    public ResponseEntity<ProductTag> updateProductTag(@PathVariable("id") String productTagId,
            @RequestBody ProductTag productTag) {
        productTag.setProductTagId(productTagId);
        ProductTag updatedProductTag = productTagService.updateProductTag(productTag);
        if (updatedProductTag != null) {
            return new ResponseEntity<>(updatedProductTag, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete ProductTag REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductTag(@PathVariable("id") String productTagId) {
        productTagService.deleteProductTag(productTagId);
        return new ResponseEntity<>("ProductTag successfully deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProductTagsByProductId(@PathVariable("productId") String productId) {
        productTagService.deleteProductTagsByProductId(productId);
        return new ResponseEntity<>("All ProductTags with productId " + productId + " successfully deleted!",
                HttpStatus.OK);
    }

    @DeleteMapping("/tag/{tagId}")
    public ResponseEntity<String> deleteProductTagsByTagId(@PathVariable("tagId") String tagId) {
        productTagService.deleteProductTagsByTagId(tagId);
        return new ResponseEntity<>("All ProductTags with tagId " + tagId + " successfully deleted!", HttpStatus.OK);
    }

    // Lấy tất cả ProductTag có chứa productId
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductTag>> getProductTagsByProductId(@PathVariable("productId") String productId) {
        List<ProductTag> productTags = productTagService.getProductTagsByProductId(productId);
        return new ResponseEntity<>(productTags, HttpStatus.OK);
    }

    //Xóa tất cả ProductTag có chứa tagId nằm trong khu vực có productId
    @DeleteMapping("/product/{productId}/tag/{tagId}")
    public ResponseEntity<String> deleteProductTagsByTagIdFromProduct(@PathVariable("productId") String productId,
            @PathVariable("tagId") String tagId) {
        productTagService.deleteProductTagsByTagIdFromProduct(productId, tagId);
        return new ResponseEntity<>(
                "ProductTags with tagId " + tagId + " from productId " + productId + " successfully deleted!",
                HttpStatus.OK);
    }

}
