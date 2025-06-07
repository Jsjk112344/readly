package com.readly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.readly.model.ReadingEntry;

public interface ReadingEntryRepository extends JpaRepository<ReadingEntry, Long> {
    List<ReadingEntry> findByReaderId(Long readerId);

    List<ReadingEntry> findByReaderIdAndStatus(Long readerId, String status);

}
