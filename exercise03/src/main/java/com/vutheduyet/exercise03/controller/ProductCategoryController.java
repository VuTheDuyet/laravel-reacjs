package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.ProductCategory;
import com.vutheduyet.exercise03.service.ProductCategoryService;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RestController
@AllArgsConstructor
@RequestMapping("api/productCategories")
public class ProductCategoryController {

    private ProductCategoryService productCategoryService;

    // Create ProductCategory REST API
    @PostMapping
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        ProductCategory savedProductCategory = productCategoryService.createProductCategory(productCategory);
        return new ResponseEntity<>(savedProductCategory, HttpStatus.CREATED);
    }

    // Get ProductCategory by id REST API
    // http://localhost:8080/api/productCategories/{id}
    @GetMapping("{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable("id") String productCategoryId) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(productCategoryId);
        return new ResponseEntity<>(productCategory, HttpStatus.OK);
    }

    // Get All ProductCategories REST API
    // http://localhost:8080/api/productCategories
    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }

    // Update ProductCategory REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/productCategories/{id}
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable("id") String productCategoryId,
            @RequestBody ProductCategory productCategory) {
        productCategory.setProductCategoryId(productCategoryId);
        ProductCategory updatedProductCategory = productCategoryService.updateProductCategory(productCategory);
        if (updatedProductCategory != null) {
            return new ResponseEntity<>(updatedProductCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete ProductCategory REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductCategory(@PathVariable("id") String productCategoryId) {
        productCategoryService.deleteProductCategory(productCategoryId);
        return new ResponseEntity<>("ProductCategory successfully deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProductCategoriesByProductId(@PathVariable("productId") String productId) {
        productCategoryService.deleteProductCategoriesByProductId(productId);
        return new ResponseEntity<>("All ProductCategories with productId " + productId + " successfully deleted!",
                HttpStatus.OK);
    }

    // Lấy tất cả ProductCategory có chứa productId
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductCategory>> getProductCategoriesByProductId(
            @PathVariable("productId") String productId) {
        List<ProductCategory> productCategories = productCategoryService.getProductCategoriesByProductId(productId);
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }

}
