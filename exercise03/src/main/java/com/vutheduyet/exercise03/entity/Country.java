package com.vutheduyet.exercise03.entity;

import java.util.List;

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
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer D;

    @Column(name = "iso", length = 2, nullable = false)
    private String iso;

    @Column(name = "name", length = 80, nullable = false)
    private String name;

    @Column(name = "upper_name", length = 80, nullable = false)
    private String upperName;

    @Column(name = "iso3", length = 3)
    private String iso3;

    @Column(name = "num_code")
    private Short numCode;

    @Column(name = "phone_code", nullable = false)
    private Integer phoneCode;

    @JsonManagedReference("Supply_Country")
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Supply> supplies;

    @JsonManagedReference("ShippingCountry_Country")
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<ShippingCountry> shippingCountries;

    
}