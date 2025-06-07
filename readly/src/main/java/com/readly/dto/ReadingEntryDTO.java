package com.readly.dto;

import java.time.LocalDate;

import com.readly.model.Book;
import com.readly.model.Reader;
import com.readly.model.ReadingEntry;

import lombok.Getter;

@Getter
public class ReadingEntryDTO {
    private Long id;
    private String status;
    private int rating;
    private String review;
    private LocalDate startedOn;
    private LocalDate finishedOn;
    private Book book;
    private Reader reader;

    public ReadingEntryDTO(Long id, String status, int rating, String review,
                           LocalDate startedOn, LocalDate finishedOn,
                           Book book, Reader reader) {
        this.id = id;
        this.status = status;
        this.rating = rating;
        this.review = review;
        this.startedOn = startedOn;
        this.finishedOn = finishedOn;
        this.book = book;
        this.reader = reader;
    }
    
    public static ReadingEntryDTO fromEntity(ReadingEntry entry) {
        return new ReadingEntryDTO(
            entry.getId(),
            entry.getStatus(),
            entry.getRating(),
            entry.getReview(),
            entry.getStartedOn(),
            entry.getFinishedOn(),
            entry.getBook(),
            entry.getReader()
        );
    }   


}
