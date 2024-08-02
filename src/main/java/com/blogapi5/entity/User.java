package com.blogapi5.entity;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
    )
      private Set<Role> roles = new HashSet<>();

//    roles=[]
//    roles=[Role(id=1, name=ROLE_ADMIN)]
}
