package com.readly.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;
    private int pages;

    private LocalDate publishedDate;

    public Book() {}

    public Book(String title, String author, String genre, int pages, LocalDate publishedDate) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.publishedDate = publishedDate;
    }

}
