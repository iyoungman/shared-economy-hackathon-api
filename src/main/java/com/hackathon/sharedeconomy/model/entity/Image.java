package com.hackathon.sharedeconomy.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long id;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private ForSale forSale;

    public Image() {
    }

    @Builder
    public Image(String path, ForSale forSale) {
        this.path = path;
        this.forSale = forSale;
    }
}
