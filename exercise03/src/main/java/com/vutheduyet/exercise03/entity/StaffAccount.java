package com.vutheduyet.exercise03.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"productsCreate", "galleriesCreate", "tagsCreate","categoriesCreate","productsUpdate"
,"galleriesUpdate","tagsUpdate","categoriesUpdate"})
@Entity
@Table(name = "staff_accounts")
public class StaffAccount {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String staffAccountId;

    @JsonBackReference("StaffAccount_Role")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role; 

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", columnDefinition = "TEXT NOT NULL")
    private String passwordHash;

    @Column(name = "active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean active;

    @Column(name = "image")
    private String image;

    @Column(name = "placeholder")
    private String placeholder;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private StaffAccount createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private StaffAccount updatedBy;

    @JsonManagedReference("Notification_StaffAccount")
    @OneToMany(mappedBy = "staffAccount", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @JsonManagedReference("attributeCreate_StaffAccount")
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Attribute> attributesCreate;

    @JsonManagedReference("attributeUpdate_StaffAccount")
    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<Attribute> attributesUpdate;

    @JsonManagedReference("SupplyCreate_StaffAccount")
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Supply> suppliesCreate;

    @JsonManagedReference("SupplyUpdate_StaffAccount")
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Supply> suppliesUpdate;

    @JsonManagedReference("couponCreate_StaffAccount")
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Coupon> couponsCreate;

    @JsonManagedReference("couponUpdate_StaffAccount")
    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<Coupon> couponsUpdate;

    @JsonIgnoreProperties("createdBy")
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Gallery> galleriesCreate;

    @JsonIgnoreProperties("updatedBy")
    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<Gallery> galleriesUpdate;

    @JsonManagedReference("OrderUpdate_StaffAccount")
    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<Order> ordersUpdate;

    @JsonManagedReference("orderStatusCreate_StaffAccount")
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<OrderStatus> orderStatusCreate;

    @JsonManagedReference("orderStatusUpdate_StaffAccount")
    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<OrderStatus> orderStatusUpdate;

    @JsonIgnoreProperties("createdBy")
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Product> productsCreate;

    @JsonIgnoreProperties("updatedBy")
    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<Product> productsUpdate;

    @JsonManagedReference("shippingCreate_StaffAccount")
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Shipping> shippingsCreate;

    @JsonManagedReference("shippingUpdate_StaffAccount")
    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<Shipping> shippingsUpdate;

    @JsonManagedReference("slideShowCreate_StaffAccount")
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<SlideShow> slideShowsCreate;

    @JsonManagedReference("slideShowUpdate_StaffAccount")
    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<SlideShow> slideShowsUpdate;

    @JsonIgnoreProperties("createdBy")
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Tag> tagsCreate;

    @JsonIgnoreProperties("updatedBy")
    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<Tag> tagsUpdate;

    @JsonIgnoreProperties("createdBy")
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Category> categoriesCreate;

    @JsonIgnoreProperties("updatedBy")
    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<Category> categoriesUpdate;
    

}