package com.readly.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readly.dto.ReadingEntryDTO;
import com.readly.model.Book;
import com.readly.model.Reader;
import com.readly.model.ReadingEntry;
import com.readly.repository.BookRepository;
import com.readly.repository.ReaderRepository;
import com.readly.repository.ReadingEntryRepository;

@RestController
@RequestMapping("/api/entries")
public class ReadingEntryController {

    private final ReadingEntryRepository entryRepo;
    private final ReaderRepository readerRepo;
    private final BookRepository bookRepo;

    public ReadingEntryController(ReadingEntryRepository entryRepo, ReaderRepository readerRepo, BookRepository bookRepo) {
        this.entryRepo = entryRepo;
        this.readerRepo = readerRepo;
        this.bookRepo = bookRepo;
    }

    

    @PostMapping
    public ReadingEntryDTO createEntry(@RequestBody EntryRequest req) {
        Reader reader = readerRepo.findById(req.readerId()).orElseThrow();
        Book book = bookRepo.findById(req.bookId()).orElseThrow();
        ReadingEntry entry = new ReadingEntry(reader, book, req.status(), req.rating(), req.review(), req.startedOn(), req.finishedOn());
        return ReadingEntryDTO.fromEntity(entryRepo.save(entry));
    }

    @GetMapping("/reader/{readerId}")
    public List<ReadingEntryDTO> getEntriesByReader(@PathVariable Long readerId) {
        return entryRepo.findByReaderId(readerId).stream()
            .map(ReadingEntryDTO::fromEntity)
            .toList();
    }


    @GetMapping("/reader/{readerId}/status/{status}")
    public List<ReadingEntryDTO> getEntriesByReaderAndStatus(@PathVariable Long readerId, @PathVariable String status) {
        return entryRepo.findByReaderIdAndStatus(readerId, status).stream()
            .map(ReadingEntryDTO::fromEntity)
            .toList();
    }





    @PutMapping("/{id}")
    public ReadingEntry updateEntry(@PathVariable Long id, @RequestBody EntryRequest req) {
        ReadingEntry entry = entryRepo.findById(id).orElseThrow();

        // Update fields
        entry.setStatus(req.status());
        entry.setRating(req.rating());
        entry.setReview(req.review());
        entry.setStartedOn(req.startedOn());
        entry.setFinishedOn(req.finishedOn());

        return entryRepo.save(entry);
    }
    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        entryRepo.deleteById(id);
    }


    // DTO to accept incoming JSON
    record EntryRequest(
        Long readerId,
        Long bookId,
        String status,
        int rating,
        String review,
        LocalDate startedOn,
        LocalDate finishedOn
    ) {}
}
