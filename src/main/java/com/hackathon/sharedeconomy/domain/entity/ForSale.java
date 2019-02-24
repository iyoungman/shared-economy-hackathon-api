package com.hackathon.sharedeconomy.domain.entity;

/**
 * Created by YoungMan on 2019-02-14.
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hackathon.sharedeconomy.domain.enums.SaleType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "for_sale_tbl")
@Component
public class ForSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;

    @Column(unique = true)
    private String name;

    private String saleType = SaleType.SALE.toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "forSale", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "forSale")
    @JsonManagedReference
    private List<Shopping> shoppings = new ArrayList<>();

    public ForSale() {
    }

    @Builder
    public ForSale(Long price, String name, User user, List<Image> images, List<Shopping> shoppings) {
        this.price = price;
        this.name = name;
        this.saleType = SaleType.SALE.toString();
        this.user = user;
        this.images = images;
        this.shoppings = shoppings;
    }

    @Override
    public String toString() {
        return "ForSale{" +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", saleType=" + saleType +
                ", user=" + user +
                ", images=" + images +
                ", shoppings=" + shoppings +
                '}';
    }
}
