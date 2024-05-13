package com.vutheduyet.exercise03.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_shipping_info")
public class ProductShippingInfo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String productShippingInfoId;

    @JsonBackReference("ShippingInfo_Product")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "weight_unit", columnDefinition = "VARCHAR(10) CHECK (weight_unit IN ('g', 'kg'))")
    private String weightUnit;

    @Column(name = "volume", nullable = false)
    private double volume;

    @Column(name = "volume_unit", columnDefinition = "VARCHAR(10) CHECK (volume_unit IN ('l', 'ml'))")
    private String volumeUnit;

    @Column(name = "dimension_width", nullable = false)
    private double dimensionWidth;

    @Column(name = "dimension_height", nullable = false)
    private double dimensionHeight;

    @Column(name = "dimension_depth", nullable = false)
    private double dimensionDepth;

    @Column(name = "dimension_unit", columnDefinition = "VARCHAR(10) CHECK (dimension_unit IN ('l', 'ml'))")
    private String dimensionUnit;
    
}