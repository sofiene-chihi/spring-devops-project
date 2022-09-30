package com.example.devopsproject.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany
    private List<Pet> pets;

    public Owner(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Owner() {
    }


}
