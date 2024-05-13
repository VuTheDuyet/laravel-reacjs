package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.SlideShow;
import com.vutheduyet.exercise03.service.SlideShowService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/slideShows")
public class SlideShowController {

    private SlideShowService slideShowService;

    // Create SlideShow REST API
    @PostMapping
    public ResponseEntity<SlideShow> createSlideShow(@RequestBody SlideShow slideShow) {
        SlideShow savedSlideShow = slideShowService.createSlideShow(slideShow);
        return new ResponseEntity<>(savedSlideShow, HttpStatus.CREATED);
    }

    // Get SlideShow by id REST API
    // http://localhost:8080/api/slideShows/{id}
    @GetMapping("{id}")
    public ResponseEntity<SlideShow> getSlideShowById(@PathVariable("id") String slideShowId) {
        SlideShow slideShow = slideShowService.getSlideShowById(slideShowId);
        return new ResponseEntity<>(slideShow, HttpStatus.OK);
    }

    // Get All SlideShows REST API
    // http://localhost:8080/api/slideShows
    @GetMapping
    public ResponseEntity<List<SlideShow>> getAllSlideShows() {
        List<SlideShow> slideShows = slideShowService.getAllSlideShows();
        return new ResponseEntity<>(slideShows, HttpStatus.OK);
    }

    // Update SlideShow REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/slideShows/{id}
    public ResponseEntity<SlideShow> updateSlideShow(@PathVariable("id") String slideShowId,
                                                      @RequestBody SlideShow slideShow) {
        slideShow.setSlideShowId(slideShowId);
        SlideShow updatedSlideShow = slideShowService.updateSlideShow(slideShow);
        if (updatedSlideShow != null) {
            return new ResponseEntity<>(updatedSlideShow, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete SlideShow REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSlideShow(@PathVariable("id") String slideShowId) {
        slideShowService.deleteSlideShow(slideShowId);
        return new ResponseEntity<>("SlideShow successfully deleted!", HttpStatus.OK);
    }
}
