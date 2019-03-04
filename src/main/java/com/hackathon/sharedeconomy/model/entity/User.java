package com.hackathon.sharedeconomy.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hackathon.sharedeconomy.model.enums.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_tbl")
public class User {

    @Id
    private String id;

    private String pw;

    private String name;

    private String phoneNumber;

    private String address;

    private Integer age;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<ForSale> forSales = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Shopping> shoppings = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public User() {
    }

    @Builder
    public User(String id, String pw, String name, String phoneNumber, String address, Integer age, List<ForSale> forSales, List<Shopping> shoppings, RoleType role) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
        this.forSales = forSales;
        this.shoppings = shoppings;
        this.role = role;
    }
}
