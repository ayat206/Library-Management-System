package com.code81.library_management.logic.service_impl;

import com.code81.library_management.data.entity.Book;
import com.code81.library_management.data.entity.Member;
import com.code81.library_management.data.entity.BorrowTransaction;
import com.code81.library_management.data.repository.BookRepository;
import com.code81.library_management.data.repository.BorrowTransactionRepository;
import com.code81.library_management.data.repository.MemberRepository;
import com.code81.library_management.logic.service.BorrowService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    private final BorrowTransactionRepository transactionRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;


    public BorrowServiceImpl(BorrowTransactionRepository transactionRepository,
                             MemberRepository memberRepository,
                             BookRepository bookRepository) {
        this.transactionRepository = transactionRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BorrowTransaction borrowBook(Long memberId, Long bookId) {
        if (transactionRepository.findByBookIdAndReturnedFalse(bookId).isPresent()) {
            throw new RuntimeException("This book is already borrowed and not returned");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        BorrowTransaction transaction = new BorrowTransaction(member, book);
        transaction.setBorrowDate(LocalDate.now());
        transaction.setDueDate(transaction.getBorrowDate().plusDays(14));
        transaction.setReturned(false);

        return transactionRepository.save(transaction);
    }

    @Override
    public BorrowTransaction returnBook(Long transactionId) {
        BorrowTransaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if (transaction.isReturned()) {
            throw new RuntimeException("This book has already been returned");
        }

        transaction.setReturned(true);
        transaction.setReturnDate(LocalDate.now());

        return transactionRepository.save(transaction);
    }

    @Override
    public List<BorrowTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<BorrowTransaction> getActiveTransactions() {
        return transactionRepository.findAll()
                .stream()
                .filter(tx -> !tx.isReturned())
                .toList();
    }

    @Override
    public List<BorrowTransaction> getOverdueTransactions() {
        LocalDate now = LocalDate.now();
        return transactionRepository.findAll()
                .stream()
                .filter(tx -> !tx.isReturned() && tx.getDueDate() != null && tx.getDueDate().isBefore(now))
                .toList();
    }

    @Override
    public List<BorrowTransaction> getTransactionsByMember(Long memberId) {
        return transactionRepository.findAll()
                .stream()
                .filter(tx -> tx.getMember().getId().equals(memberId))
                .toList();
    }
}
