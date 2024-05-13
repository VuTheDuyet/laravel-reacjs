package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.CardItem;
import com.vutheduyet.exercise03.repository.CardItemRepository;
import com.vutheduyet.exercise03.service.CardItemService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CardItemServiceImpl implements CardItemService {
    private CardItemRepository cardItemRepository;

    @Override
    public CardItem createCardItem(CardItem cardItem) {
        return cardItemRepository.save(cardItem);
    }

    @Override
    public CardItem getCardItemById(String cardItemId) {
        Optional<CardItem> optionalCardItem = cardItemRepository.findById(cardItemId);
        return optionalCardItem.orElse(null);
    }

    @Override
    public List<CardItem> getAllCardItems() {
        return cardItemRepository.findAll();
    }

    @Override
    public CardItem updateCardItem(CardItem cardItem) {
        Optional<CardItem> optionalExistingCardItem = cardItemRepository.findById(cardItem.getCardItemId());
        if (optionalExistingCardItem.isPresent()) {
            CardItem existingCardItem = optionalExistingCardItem.get();
            existingCardItem.setCard(cardItem.getCard());
            existingCardItem.setProduct(cardItem.getProduct());
            existingCardItem.setQuantity(cardItem.getQuantity());

            CardItem updatedCardItem = cardItemRepository.save(existingCardItem);
            return updatedCardItem;
        }

        return null;
    }

    @Override
    public void deleteCardItem(String cardItemId) {
        cardItemRepository.deleteById(cardItemId);
    }
}
