package com.code81.library_management.data.repository;

import com.code81.library_management.data.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    //List<Author> searchAuthorsByName(String keyword);
    Optional<Author> findByName(String name);
}
