package com.code81.library_management.data.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrow_transactions")
public class BorrowTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //l member y2dr y3ml aktr mn transaction bs kol borrow transaction associated b member wa7d
    @ManyToOne
    private Member member;

    //many borrow transaction to one book
    @ManyToOne
    private Book book;

    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private boolean returned;

    //Many BorrowTransactions → One SystemUser
    @ManyToOne
    @JoinColumn(name = "created_by")
    private SystemUser createdBy;

    public BorrowTransaction() {
    }

    public BorrowTransaction(Member member, Book book) {
        this.member = member;
        this.book = book;
    }

    public BorrowTransaction(Member member, Book book, LocalDate borrowDate, LocalDate dueDate, boolean returned, SystemUser createdBy) {
        this.member = member;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public SystemUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(SystemUser createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}



