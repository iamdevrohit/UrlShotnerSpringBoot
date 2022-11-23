package com.urlshortner.demo.urlshortnerdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ShortUrlTable")
@AllArgsConstructor
@NoArgsConstructor
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private Long autherid;
    String shortkey;
    String shorturl;
    String orignalurl;

    @CreationTimestamp
    private LocalDateTime created_on;
}
