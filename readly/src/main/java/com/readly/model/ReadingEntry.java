package com.readly.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ReadingEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Reader reader;

    @ManyToOne
    private Book book;

    private String status; // "To Read", "Reading", "Finished"
    private int rating;    // e.g., 1 to 5
    private String review;

    private LocalDate startedOn;
    private LocalDate finishedOn;

    public ReadingEntry() {}

    public ReadingEntry(Reader reader, Book book, String status, int rating, String review,
                        LocalDate startedOn, LocalDate finishedOn) {
        this.reader = reader;
        this.book = book;
        this.status = status;
        this.rating = rating;
        this.review = review;
        this.startedOn = startedOn;
        this.finishedOn = finishedOn;
    }


}
