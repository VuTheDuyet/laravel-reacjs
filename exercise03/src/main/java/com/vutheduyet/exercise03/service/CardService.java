package com.vutheduyet.exercise03.service;

import java.util.List;

import com.vutheduyet.exercise03.entity.Card;

public interface CardService {
    Card createCard(Card card);
    Card getCardById(String cardId);
    List<Card> getAllCards();
    Card updateCard(Card card);
    void deleteCard(String cardId);
}