package com.readly.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readly.model.Reader;
import com.readly.repository.ReaderRepository;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    private final ReaderRepository readerRepo;

    public ReaderController(ReaderRepository readerRepo) {
        this.readerRepo = readerRepo;
    }

    @PostMapping
    public Reader createReader(@RequestBody Reader reader) {
        return readerRepo.save(reader);
    }

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerRepo.findAll();
    }
}
