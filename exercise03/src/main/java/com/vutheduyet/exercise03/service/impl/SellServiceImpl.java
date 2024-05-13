package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Sell;
import com.vutheduyet.exercise03.repository.SellRepository;
import com.vutheduyet.exercise03.service.SellService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SellServiceImpl implements SellService {
    private SellRepository sellRepository;

    @Override
    public Sell createSell(Sell sell) {
        return sellRepository.save(sell);
    }

    @Override
    public Sell getSellById(String sellId) {
        Optional<Sell> optionalSell = sellRepository.findById(sellId);
        return optionalSell.orElse(null);
    }

    @Override
    public List<Sell> getAllSells() {
        return sellRepository.findAll();
    }

    @Override
    public Sell updateSell(Sell sell) {
        Optional<Sell> optionalExistingSell = sellRepository.findById(sell.getSellId());
        if (optionalExistingSell.isPresent()) {
            Sell existingSell = optionalExistingSell.get();
            existingSell.setPrice(sell.getPrice());
            existingSell.setQuantity(sell.getQuantity());

            Sell updatedSell = sellRepository.save(existingSell);
            return updatedSell;
        }

        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy bán hàng tồn tại
    }

    @Override
    public void deleteSell(String sellId) {
        sellRepository.deleteById(sellId);
    }
}
