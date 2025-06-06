package com.readly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.readly.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
