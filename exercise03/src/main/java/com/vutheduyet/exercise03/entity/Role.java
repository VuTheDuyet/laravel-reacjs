package com.vutheduyet.exercise03.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String roleId;

    @Column(nullable = false,name = "role_name")
    private String roleName;

    @Column(name = "privileges", columnDefinition = "TEXT")
    private String privileges;

  

    @JsonManagedReference("StaffAccount_Role")
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<StaffAccount> staffAccounts;

    
}