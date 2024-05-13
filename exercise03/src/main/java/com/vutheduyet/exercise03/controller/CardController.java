package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.Card;
import com.vutheduyet.exercise03.service.CardService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/cards")
public class CardController {

    private CardService cardService;

    // Create Card REST API
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card savedCard = cardService.createCard(card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    // Get Card by id REST API
    // http://localhost:8080/api/cards/{id}
    @GetMapping("{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id") String cardId) {
        Card card = cardService.getCardById(cardId);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    // Get All Cards REST API
    // http://localhost:8080/api/cards
    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.getAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    // Update Card REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/cards/{id}
    public ResponseEntity<Card> updateCard(@PathVariable("id") String cardId,
                                            @RequestBody Card card) {
        card.setCardId(cardId);
        Card updatedCard = cardService.updateCard(card);
        if (updatedCard != null) {
            return new ResponseEntity<>(updatedCard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Card REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCard(@PathVariable("id") String cardId) {
        cardService.deleteCard(cardId);
        return new ResponseEntity<>("Card successfully deleted!", HttpStatus.OK);
    }
}
