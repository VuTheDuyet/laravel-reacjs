package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.Sell;
import com.vutheduyet.exercise03.service.SellService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/sells")
public class SellController {

    private SellService sellService;

    // Create Sell REST API
    @PostMapping
    public ResponseEntity<Sell> createSell(@RequestBody Sell sell) {
        Sell savedSell = sellService.createSell(sell);
        return new ResponseEntity<>(savedSell, HttpStatus.CREATED);
    }

    // Get Sell by id REST API
    // http://localhost:8080/api/sells/{id}
    @GetMapping("{id}")
    public ResponseEntity<Sell> getSellById(@PathVariable("id") String sellId) {
        Sell sell = sellService.getSellById(sellId);
        return new ResponseEntity<>(sell, HttpStatus.OK);
    }

    // Get All Sells REST API
    // http://localhost:8080/api/sells
    @GetMapping
    public ResponseEntity<List<Sell>> getAllSells() {
        List<Sell> sells = sellService.getAllSells();
        return new ResponseEntity<>(sells, HttpStatus.OK);
    }

    // Update Sell REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/sells/{id}
    public ResponseEntity<Sell> updateSell(@PathVariable("id") String sellId,
                                            @RequestBody Sell sell) {
        sell.setSellId(sellId);
        Sell updatedSell = sellService.updateSell(sell);
        if (updatedSell != null) {
            return new ResponseEntity<>(updatedSell, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Sell REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSell(@PathVariable("id") String sellId) {
        sellService.deleteSell(sellId);
        return new ResponseEntity<>("Sell successfully deleted!", HttpStatus.OK);
    }
}
