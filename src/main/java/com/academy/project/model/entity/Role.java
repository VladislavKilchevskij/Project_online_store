package com.academy.project.model.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String roleName;
    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    private List<User> users;
}
