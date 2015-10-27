package com.ads.in.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by bchittepu on 10/27/15.
 */
@Entity
@Table(name="CITY")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;

    @Size(min=1, max=50)
    @Column(name = "cityName", nullable = false)
    private String cityName;

}
