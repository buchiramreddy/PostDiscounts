package com.ads.in.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by bchittepu on 10/27/15.
 */
@Entity
@Table(name="BRAND")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandId;

    @Size(min=1, max=50)
    @Column(name = "brandName", nullable = false)
    private String brandName;
}
