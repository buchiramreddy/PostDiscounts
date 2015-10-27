package com.ads.in.model;

import javax.persistence.*;

/**
 * Created by bchittepu on 10/27/15.
 */
@Entity
@Table(name="PRODUCTREVIEWS")
public class ProductReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewsId;
}
