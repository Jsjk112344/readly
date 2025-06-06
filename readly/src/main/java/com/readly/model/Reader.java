package com.readly.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // @OneToMany(mappedBy = "reader")
    // private List<ReadingEntry> entries;

    public Reader() {}

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
