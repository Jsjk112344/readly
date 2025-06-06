package com.readly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.readly.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
