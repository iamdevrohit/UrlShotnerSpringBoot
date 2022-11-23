package com.urlshortner.demo.urlshortnerdemo.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "Usertable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;
    private boolean active;
    private String roles;

}
