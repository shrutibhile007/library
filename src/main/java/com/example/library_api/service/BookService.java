package com.example.library_api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.library_api.model.Book;

public class BookService {
    private final Map<Integer, Book> bookMap = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookMap.values());
    }

    public Book getBookById(Integer id) {
        return bookMap.get(id);
    }

    public Book addBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        int id = idCounter.getAndIncrement();
        book.setId(id);
        bookMap.put(id, book);
        return book;
    }

    public boolean deleteBook(Integer id) {
        return bookMap.remove(id) != null;
    }

    public Book updateAvailability(Integer id, boolean available) {
        Book book = bookMap.get(id);
        if (book != null) {
            book.setAvailable(available);
        }
        return book;
    }
}
