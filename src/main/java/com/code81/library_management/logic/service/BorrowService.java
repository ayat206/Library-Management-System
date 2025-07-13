package com.code81.library_management.logic.service;

import com.code81.library_management.data.entity.BorrowTransaction;
import com.code81.library_management.web.dto.BorrowTransactionDTO;

import java.util.List;

public interface BorrowService {

    BorrowTransactionDTO borrowBook(Long memberId, Long bookId, Long createdById);
    BorrowTransactionDTO returnBook(Long transactionId);
    List<BorrowTransactionDTO> getAllTransactions();
    List<BorrowTransactionDTO> getActiveTransactions();
    List<BorrowTransactionDTO> getOverdueTransactions();
    List<BorrowTransactionDTO> getTransactionsByMember(Long memberId);
}
