package com.ads.in.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by bchittepu on 10/27/15.
 */
@Entity
@Table(name="OFFERTYPE")
public class OfferType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int offerTypeId;

    @Size(min=1, max=50)
    @Column(name = "offerName", nullable = false)
    private String offerName;
}
