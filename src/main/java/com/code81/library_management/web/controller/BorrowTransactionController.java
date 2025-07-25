package com.code81.library_management.web.controller;

import com.code81.library_management.data.entity.BorrowTransaction;
import com.code81.library_management.logic.service.BorrowService;
import com.code81.library_management.web.dto.BorrowRequest;
import com.code81.library_management.web.dto.ReturnRequest;
import com.code81.library_management.web.dto.BorrowTransactionDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class BorrowTransactionController {
    private final BorrowService borrowService;

    public BorrowTransactionController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/borrow")
    public BorrowTransactionDTO borrowBook(@RequestBody BorrowRequest request) {
        return borrowService.borrowBook(request.getMemberId(), request.getBookId(), request.getCreatedById());
    }

    @PostMapping("/return")
    public BorrowTransactionDTO returnBook(@RequestBody ReturnRequest request) {
        return borrowService.returnBook(request.getTransactionId());
    }

    @GetMapping
    public List<BorrowTransactionDTO> getAllTransactions() {
        return borrowService.getAllTransactions();
    }

    @GetMapping("/active")
    public List<BorrowTransactionDTO> getActiveTransactions() {
        return borrowService.getActiveTransactions();
    }

    @GetMapping("/overdue")
    public List<BorrowTransactionDTO> getOverdueTransactions() {
        return borrowService.getOverdueTransactions();
    }

    @GetMapping("/member/{memberId}")
    public List<BorrowTransactionDTO> getTransactionsByMember(@PathVariable Long memberId) {
        return borrowService.getTransactionsByMember(memberId);
    }
}
