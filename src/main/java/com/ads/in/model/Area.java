package com.ads.in.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by bchittepu on 10/27/15.
 */
@Entity
@Table(name="AREA")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;

    @Size(min=1, max=50)
    @Column(name = "areaName", nullable = false)
    private String areaName;
}
