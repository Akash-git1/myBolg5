package com.blogapi5.entity;

import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts5")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comment = new ArrayList<>();

}
