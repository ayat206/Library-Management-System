package com.code81.library_management.logic.service;

import com.code81.library_management.data.entity.BorrowTransaction;

import java.util.List;

public interface BorrowService {
    BorrowTransaction borrowBook(Long memberId, Long bookId);
    BorrowTransaction returnBook(Long transactionId);
    List<BorrowTransaction> getAllTransactions();
    List<BorrowTransaction> getActiveTransactions();
    List<BorrowTransaction> getOverdueTransactions();
    List<BorrowTransaction> getTransactionsByMember(Long memberId);
}
