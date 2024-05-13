package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Card;
import com.vutheduyet.exercise03.repository.CardRepository;
import com.vutheduyet.exercise03.service.CardService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;

    @Override
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card getCardById(String cardId) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        return optionalCard.orElse(null);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card updateCard(Card card) {
        Optional<Card> optionalExistingCard = cardRepository.findById(card.getCardId());
        if (optionalExistingCard.isPresent()) {
            Card existingCard = optionalExistingCard.get();
            existingCard.setCustomer(card.getCustomer()); // Cập nhật thông tin khách hàng

            Card updatedCard = cardRepository.save(existingCard);
            return updatedCard;
        }

        return null;
    }

    @Override
    public void deleteCard(String cardId) {
        cardRepository.deleteById(cardId);
    }
}
