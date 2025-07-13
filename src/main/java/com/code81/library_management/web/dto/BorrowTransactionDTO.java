package com.code81.library_management.web.dto;

import java.time.LocalDate;

public class BorrowTransactionDTO {
    private Long id;
    private String memberName;
    private String bookTitle;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;
    private String createdByFullName;

    public BorrowTransactionDTO() {
    }

    public BorrowTransactionDTO(Long id, String bookTitle, String memberName,
                                LocalDate borrowDate, LocalDate dueDate,
                                LocalDate returnDate, boolean returned,
                                String createdByFullName) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.memberName = memberName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.returned = returned;
        this.createdByFullName = createdByFullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public String getCreatedByFullName() {
        return createdByFullName;
    }

    public void setCreatedByFullName(String createdByFullName) {
        this.createdByFullName = createdByFullName;
    }
}
