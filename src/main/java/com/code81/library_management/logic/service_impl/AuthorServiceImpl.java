package com.code81.library_management.logic.service_impl;

import com.code81.library_management.data.entity.Author;
import com.code81.library_management.data.repository.AuthorRepository;
import com.code81.library_management.logic.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + id));
    }

    @Override
    public Author createAuthor(Author author) {
        if (authorRepository.findByName(author.getName()).isPresent()) {
            throw new RuntimeException("Author already exists with name: " + author.getName());
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author existing = getAuthorById(id);

        if (author.getName() != null && !author.getName().isBlank()) {
            existing.setName(author.getName());
        }

        if (author.getBio() != null && !author.getBio().isBlank()) {
            existing.setBio(author.getBio());
        }

        return authorRepository.save(existing);
    }


    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with ID: " + id);
        }
        authorRepository.deleteById(id);
    }

}
