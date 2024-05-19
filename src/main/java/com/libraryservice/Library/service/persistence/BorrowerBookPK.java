/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libraryservice.Library.service.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;


/**
 * @author gajit
 */
@Embeddable
public class BorrowerBookPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "borrower_id")
    private int borrowerId;
    @Basic(optional = false)
    @Column(name = "book_id")
    private int bookId;

    public BorrowerBookPK() {
    }

    public BorrowerBookPK(int borrowerId, int bookId) {
        this.borrowerId = borrowerId;
        this.bookId = bookId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) borrowerId;
        hash += (int) bookId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BorrowerBookPK)) {
            return false;
        }
        BorrowerBookPK other = (BorrowerBookPK) object;
        if (this.borrowerId != other.borrowerId) {
            return false;
        }
        if (this.bookId != other.bookId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.resources.jpa.BorrowerBookPK[ borrowerId=" + borrowerId + ", bookId=" + bookId + " ]";
    }

}
