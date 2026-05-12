package com.example.backend.service;

import com.example.backend.entity.Book;
import com.example.backend.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Add Book
    public Book addBook(Book book) {
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    // Get All Books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get Available Books
    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailable(true);
    }

    // Search By Title
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // Search By Author
    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    // Get Book By Id
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Update Book
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
}