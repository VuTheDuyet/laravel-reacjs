package com.vutheduyet.exercise03.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "variant_options")
public class VariantOption {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String variantOptionId;

    @Column(name = "title", columnDefinition = "TEXT", nullable = false)
    private String title;

    @JsonIgnoreProperties("variantOptions")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", nullable = false)
    private Gallery gallery; 

    @JsonBackReference("VariantOption_Product")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "sale_price", nullable = false, columnDefinition = "NUMERIC(19, 2) DEFAULT 0")
    private BigDecimal salePrice;

    @Column(name = "compare_price", columnDefinition = "NUMERIC(19, 2) DEFAULT 0")
    @Check(constraints = "compare_price > sale_price OR compare_price = 0")
    private BigDecimal comparePrice;

    @Column(name = "buying_price", columnDefinition = "NUMERIC(19, 2) DEFAULT NULL")
    private BigDecimal buyingPrice;

    @Column(name = "quantity", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer quantity;

    @Column(name = "sku", length = 255)
    private String sku;

    @Column(name = "active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean active;

    @JsonManagedReference("Variant_VariantOption")
    @OneToMany(mappedBy = "variantOption2", cascade = CascadeType.ALL)
    private List<Variant> variants;
 
}