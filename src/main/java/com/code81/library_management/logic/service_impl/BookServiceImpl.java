package com.code81.library_management.logic.service_impl;

import com.code81.library_management.data.entity.Author;
import com.code81.library_management.data.entity.Book;
import com.code81.library_management.data.entity.Category;
import com.code81.library_management.data.entity.Publisher;
import com.code81.library_management.data.repository.AuthorRepository;
import com.code81.library_management.data.repository.BookRepository;
import com.code81.library_management.data.repository.CategoryRepository;
import com.code81.library_management.data.repository.PublisherRepository;
import com.code81.library_management.logic.service.BookService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public Book createBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new RuntimeException("Book already exists");
        }

        // load publisher
        Publisher publisher = publisherRepository.findById(book.getPublisher().getId())
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
        book.setPublisher(publisher);

        // load category
        Category category = categoryRepository.findById(book.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        book.setCategory(category);

        // load authors
        Set<Author> fullAuthors = new HashSet<>();
        for (Author author : book.getAuthors()) {
            Author found = authorRepository.findById(author.getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            fullAuthors.add(found);
        }
        book.setAuthors(fullAuthors);

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existing.setTitle(book.getTitle());
        existing.setLanguage(book.getLanguage());
        existing.setPublicationYear(book.getPublicationYear());
        existing.setIsbn(book.getIsbn());
        existing.setEdition(book.getEdition());
        existing.setSummary(book.getSummary());
        existing.setCoverImageUrl(book.getCoverImageUrl());

        // reload relationships
        Publisher publisher = publisherRepository.findById(book.getPublisher().getId())
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
        existing.setPublisher(publisher);

        Category category = categoryRepository.findById(book.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setCategory(category);

        Set<Author> fullAuthors = new HashSet<>();
        for (Author author : book.getAuthors()) {
            Author found = authorRepository.findById(author.getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            fullAuthors.add(found);
        }
        existing.setAuthors(fullAuthors);

        return bookRepository.save(existing);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
