package com.code81.library_management.logic.service_impl;

import com.code81.library_management.data.entity.Book;
import com.code81.library_management.data.entity.Member;
import com.code81.library_management.data.entity.BorrowTransaction;
import com.code81.library_management.data.entity.SystemUser;
import com.code81.library_management.data.repository.BookRepository;
import com.code81.library_management.data.repository.SystemUserRepository;
import com.code81.library_management.data.repository.BorrowTransactionRepository;
import com.code81.library_management.data.repository.MemberRepository;
import com.code81.library_management.logic.service.BorrowService;
import com.code81.library_management.logic.service.UserLogService;
import com.code81.library_management.web.dto.BorrowTransactionDTO;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    private final BorrowTransactionRepository transactionRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final SystemUserRepository systemUserRepository;
    private final UserLogService userLogService;


    public BorrowServiceImpl(BorrowTransactionRepository transactionRepository,
                             MemberRepository memberRepository,
                             BookRepository bookRepository,
                             SystemUserRepository systemUserRepository,
                             UserLogService userLogService) {
        this.transactionRepository = transactionRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.systemUserRepository = systemUserRepository;
        this.userLogService = userLogService;
    }
    @Override
    public BorrowTransactionDTO borrowBook(Long memberId, Long bookId, Long createdById) {
        if (transactionRepository.findByBookIdAndReturnedFalse(bookId).isPresent()) {
            throw new RuntimeException("This book is already borrowed and not returned");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        SystemUser createdBy = systemUserRepository.findById(createdById)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BorrowTransaction transaction = new BorrowTransaction(member, book);
        transaction.setBorrowDate(LocalDate.now());
        transaction.setDueDate(transaction.getBorrowDate().plusDays(14));
        transaction.setReturned(false);
        transaction.setCreatedBy(createdBy);

        BorrowTransaction savedTransaction = transactionRepository.save(transaction);

        userLogService.logAction(createdBy,
                "Borrowed book '" + book.getTitle() + "' for member '" + member.getFullName() + "'");

        return mapToDTO(savedTransaction);
    }


    @Override
    public BorrowTransactionDTO returnBook(Long transactionId) {
        BorrowTransaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if (transaction.isReturned()) {
            throw new RuntimeException("This book has already been returned");
        }

        transaction.setReturned(true);
        transaction.setReturnDate(LocalDate.now());

        BorrowTransaction saved = transactionRepository.save(transaction);

        if (saved.getCreatedBy() != null) {
            userLogService.logAction(
                    saved.getCreatedBy(),
                    "Returned book '" + saved.getBook().getTitle() + "' for member '" + saved.getMember().getFullName() + "'"
            );
        }

        return mapToDTO(saved);
    }

    @Override
    public List<BorrowTransactionDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<BorrowTransactionDTO> getActiveTransactions() {
        return transactionRepository.findAll()
                .stream()
                .filter(tx -> !tx.isReturned())
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<BorrowTransactionDTO> getOverdueTransactions() {
        LocalDate now = LocalDate.now();
        return transactionRepository.findAll()
                .stream()
                .filter(tx -> !tx.isReturned() && tx.getDueDate() != null && tx.getDueDate().isBefore(now))
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<BorrowTransactionDTO> getTransactionsByMember(Long memberId) {
        return transactionRepository.findAll()
                .stream()
                .filter(tx -> tx.getMember().getId().equals(memberId))
                .map(this::mapToDTO)
                .toList();
    }

    private BorrowTransactionDTO mapToDTO(BorrowTransaction tx) {
        return new BorrowTransactionDTO(
                tx.getId(),
                tx.getBook().getTitle(),
                tx.getMember().getFullName(),
                tx.getBorrowDate(),
                tx.getDueDate(),
                tx.getReturnDate(),
                tx.isReturned(),
                tx.getCreatedBy() != null ? tx.getCreatedBy().getFullName() : null
        );
    }
}
