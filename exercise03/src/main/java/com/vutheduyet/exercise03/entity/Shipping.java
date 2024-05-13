package com.vutheduyet.exercise03.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "shippings")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int shippingId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "display_name", nullable = false, length = 255)
    private String displayName;

    @Column(name = "active", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean active;

    @Column(name = "icon_path", columnDefinition = "TEXT")
    private String iconPath;

    @Column(name = "free_shipping", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean freeShipping;

    @Column(name = "rate_type", columnDefinition = "VARCHAR(64) CHECK (rate_type IN ('price'))")
    private String rateType;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @JsonBackReference("shippingCreate_StaffAccount")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private StaffAccount createdBy;

    @JsonBackReference("shippingUpdate_StaffAccount")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", nullable = false)
    private StaffAccount updatedBy;

   


    @JsonManagedReference("OrderItem_Shipping")
    @OneToMany(mappedBy = "shipping", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @JsonManagedReference("ShippingRate_Shipping")
    @OneToMany(mappedBy = "shipping", cascade = CascadeType.ALL)
    private List<ShippingRate> shippingRates;

    @JsonManagedReference("ShippingCountry_Shipping")
    @OneToMany(mappedBy = "shipping", cascade = CascadeType.ALL)
    private List<ShippingCountry> shippingCountries;

    

    
}