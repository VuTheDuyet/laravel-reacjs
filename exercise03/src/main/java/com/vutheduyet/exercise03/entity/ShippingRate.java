package com.vutheduyet.exercise03.entity;


import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;







@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipping_rates")
public class ShippingRate {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String shippingRateId;

    @JsonBackReference("ShippingRate_Shipping")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_id", nullable = false)
    private Shipping shipping; 

    @Column(name = "weight_unit", columnDefinition = "VARCHAR(10) CHECK (weight_unit IN ('g', 'kg'))")
    private String weightUnit;

    @Column(name = "min_value", nullable = false)
    private BigDecimal minValue;

    @Column(name = "max_value")
    private BigDecimal maxValue;

    @Column(name = "no_max", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean noMax;

    @Column(name = "price", nullable = false, columnDefinition = "NUMERIC DEFAULT 0")
    private double price;
    
}