/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libraryservice.Library.service.persistence;

import jakarta.persistence.*;

import java.io.Serializable;


/**
 * @author gajit
 */
@Entity
@Table(name = "borrower_book")
public class BorrowerBook implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BorrowerBookPK borrowerBookPK;
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Book book;
    @JoinColumn(name = "borrower_id", referencedColumnName = "borrower_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Borrower borrower;

    public BorrowerBook() {
    }

    public BorrowerBook(BorrowerBookPK borrowerBookPK) {
        this.borrowerBookPK = borrowerBookPK;
    }

    public BorrowerBook(int borrowerId, int bookId) {
        this.borrowerBookPK = new BorrowerBookPK(borrowerId, bookId);
    }

    public BorrowerBookPK getBorrowerBookPK() {
        return borrowerBookPK;
    }

    public void setBorrowerBookPK(BorrowerBookPK borrowerBookPK) {
        this.borrowerBookPK = borrowerBookPK;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (borrowerBookPK != null ? borrowerBookPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BorrowerBook)) {
            return false;
        }
        BorrowerBook other = (BorrowerBook) object;
        if ((this.borrowerBookPK == null && other.borrowerBookPK != null) || (this.borrowerBookPK != null && !this.borrowerBookPK.equals(other.borrowerBookPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.resources.jpa.BorrowerBook[ borrowerBookPK=" + borrowerBookPK + " ]";
    }

}
