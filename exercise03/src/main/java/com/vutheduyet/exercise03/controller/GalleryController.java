package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.Gallery;
import com.vutheduyet.exercise03.service.GalleryService;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE })
@RestController
@AllArgsConstructor
@RequestMapping("api/galleries")
public class GalleryController {

    private GalleryService galleryService;

    // Create Gallery REST API
    @PostMapping
    public ResponseEntity<Gallery> createGallery(@RequestBody Gallery gallery) {
        Gallery savedGallery = galleryService.createGallery(gallery);
        return new ResponseEntity<>(savedGallery, HttpStatus.CREATED);
    }

    // Get Gallery by id REST API
    // http://localhost:8080/api/galleries/{id}
    @GetMapping("{id}")
    public ResponseEntity<Gallery> getGalleryById(@PathVariable("id") String galleryId) {
        Gallery gallery = galleryService.getGalleryById(galleryId);
        return new ResponseEntity<>(gallery, HttpStatus.OK);
    }

    // Get All Galleries REST API
    // http://localhost:8080/api/galleries
    @GetMapping
    public ResponseEntity<List<Gallery>> getAllGalleries() {
        List<Gallery> galleries = galleryService.getAllGalleries();
        return new ResponseEntity<>(galleries, HttpStatus.OK);
    }

    

    // Update Gallery REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/galleries/{id}
    public ResponseEntity<Gallery> updateGallery(@PathVariable("id") String galleryId,
            @RequestBody Gallery gallery) {
        gallery.setGalleryId(galleryId);
        Gallery updatedGallery = galleryService.updateGallery(gallery);
        if (updatedGallery != null) {
            return new ResponseEntity<>(updatedGallery, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Gallery REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGallery(@PathVariable("id") String galleryId) {
        galleryService.deleteGallery(galleryId);
        return new ResponseEntity<>("Gallery successfully deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteGalleriesByProductId(@PathVariable("productId") String productId) {
        galleryService.deleteGalleriesByProductId(productId);
        return new ResponseEntity<>("All Galleries with productId " + productId + " successfully deleted!",
                HttpStatus.OK);
    }

    // Lất tất cả Gallery có chứa productId
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Gallery>> getGalleriesByProductId(@PathVariable("productId") String productId) {
        List<Gallery> galleries = galleryService.getGalleriesByProductId(productId);
        return new ResponseEntity<>(galleries, HttpStatus.OK);
    }

}
