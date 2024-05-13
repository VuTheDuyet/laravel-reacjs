package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.CardItem;
import com.vutheduyet.exercise03.service.CardItemService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/cardItems")
public class CardItemController {

    private CardItemService cardItemService;

    // Create CardItem REST API
    @PostMapping
    public ResponseEntity<CardItem> createCardItem(@RequestBody CardItem cardItem) {
        CardItem savedCardItem = cardItemService.createCardItem(cardItem);
        return new ResponseEntity<>(savedCardItem, HttpStatus.CREATED);
    }

    // Get CardItem by id REST API
    // http://localhost:8080/api/cardItems/{id}
    @GetMapping("{id}")
    public ResponseEntity<CardItem> getCardItemById(@PathVariable("id") String cardItemId) {
        CardItem cardItem = cardItemService.getCardItemById(cardItemId);
        return new ResponseEntity<>(cardItem, HttpStatus.OK);
    }

    // Get All CardItems REST API
    // http://localhost:8080/api/cardItems
    @GetMapping
    public ResponseEntity<List<CardItem>> getAllCardItems() {
        List<CardItem> cardItems = cardItemService.getAllCardItems();
        return new ResponseEntity<>(cardItems, HttpStatus.OK);
    }

    // Update CardItem REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/cardItems/{id}
    public ResponseEntity<CardItem> updateCardItem(@PathVariable("id") String cardItemId,
                                                    @RequestBody CardItem cardItem) {
        cardItem.setCardItemId(cardItemId);
        CardItem updatedCardItem = cardItemService.updateCardItem(cardItem);
        if (updatedCardItem != null) {
            return new ResponseEntity<>(updatedCardItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete CardItem REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCardItem(@PathVariable("id") String cardItemId) {
        cardItemService.deleteCardItem(cardItemId);
        return new ResponseEntity<>("CardItem successfully deleted!", HttpStatus.OK);
    }
}
