package com.vutheduyet.exercise03.service;

import java.util.List;

import com.vutheduyet.exercise03.entity.Supply;

public interface SupplyService {
    Supply createSupply(Supply product);
    Supply getSupplyById(String productId);
    List<Supply> getAllSupplys();
    Supply updateSupply(Supply product);
    void deleteSupply(String productId);
}