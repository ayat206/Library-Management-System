package com.code81.library_management.logic.service;

import com.code81.library_management.data.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
    Author createAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);

}
