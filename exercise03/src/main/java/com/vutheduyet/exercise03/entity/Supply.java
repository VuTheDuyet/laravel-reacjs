package com.vutheduyet.exercise03.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supply {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String supplyId;

    @Column(name = "supplier_name", nullable = false, length = 255)
    private String supplierName;
    
    @Column(name = "company", length = 255)
    private String company;
    
    @Column(name = "phone_number", length = 255)
    private String phoneNumber;
    
    @Column(name = "address_line1", nullable = false, columnDefinition = "TEXT")
    private String addressLine1;
    
    @Column(name = "address_line2", columnDefinition = "TEXT")
    private String addressLine2;

    @JsonBackReference("Supply_Country")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
    
    @Column(name = "city", length = 255)
    private String city;
    
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @JsonBackReference("SupplyCreate_StaffAccount")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private StaffAccount createdBy;

    @JsonBackReference("SupplyUpdate_StaffAccount")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", nullable = false)
    private StaffAccount updatedBy;

    @JsonManagedReference("ProductSupply_Supply")
    @OneToMany(mappedBy = "supply", cascade = CascadeType.ALL)
    private List<ProductSupply> productSupplies;
}