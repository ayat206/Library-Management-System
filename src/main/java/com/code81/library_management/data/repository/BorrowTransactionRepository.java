package com.code81.library_management.data.repository;

import com.code81.library_management.data.entity.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction, Long> {
    Optional<BorrowTransaction> findByBookIdAndReturnedFalse(Long bookId);
}
