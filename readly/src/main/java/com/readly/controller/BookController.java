package com.readly.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readly.model.Book;
import com.readly.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepo;

    public BookController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepo.save(book);
    }
}
