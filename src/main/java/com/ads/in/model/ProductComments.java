package com.ads.in.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by bchittepu on 10/27/15.
 */
@Entity
@Table(name="PRODUCTCOMMENTS")
public class ProductComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentsId;

    @Size(min=1, max=500)
    @Column(name = "comments", nullable = false)
    private String comments;

    @Size(min=1, max=50)
    @Column(name = "nickName", nullable = false)
    private String nickName;

    @Size(min=1, max=100)
    @Column(name = "emailId", nullable = false)
    private String emailId;


}
