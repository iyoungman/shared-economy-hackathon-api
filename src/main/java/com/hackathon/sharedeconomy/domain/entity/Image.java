package com.hackathon.sharedeconomy.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Getter
@Setter
@Entity
@Table(name = "image_tbl")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private ForSale forSale;

    public Image() {
    }

    @Builder
    public Image(String path, ForSale forSale) {
        this.path = path;
        this.forSale = forSale;
    }
}
