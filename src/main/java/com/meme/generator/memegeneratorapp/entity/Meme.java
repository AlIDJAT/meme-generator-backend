package com.meme.generator.memegeneratorapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Meme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="image_url", nullable=false)
    private String imageUrl;

    @Column(name="top_text")
    private String topText;

    @Column(name="bottom_text")
    private String bottomText;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;
}