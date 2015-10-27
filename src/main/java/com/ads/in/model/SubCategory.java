package com.ads.in.model;

import javax.persistence.*;

/**
 * Created by bchittepu on 10/27/15.
 */
@Entity
@Table(name="SUBCATEGORY")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subCategoryId;


}
