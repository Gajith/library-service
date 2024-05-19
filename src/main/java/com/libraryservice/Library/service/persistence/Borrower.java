/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libraryservice.Library.service.persistence;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


/**
 * @author gajit
 */
@Entity
@Table(name = "borrower")
public class Borrower implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "borrower_id")
    private Integer borrowerId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "borrower")
    private List<BorrowerBook> borrowerBookList;

    public Borrower() {
    }

    public Borrower(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Borrower(String name, String email) {
        this.borrowerId = borrowerId;
        this.name = name;
        this.email = email;
    }

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BorrowerBook> getBorrowerBookList() {
        return borrowerBookList;
    }

    public void setBorrowerBookList(List<BorrowerBook> borrowerBookList) {
        this.borrowerBookList = borrowerBookList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (borrowerId != null ? borrowerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Borrower)) {
            return false;
        }
        Borrower other = (Borrower) object;
        if ((this.borrowerId == null && other.borrowerId != null) || (this.borrowerId != null && !this.borrowerId.equals(other.borrowerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.resources.jpa.Borrower[ borrowerId=" + borrowerId + " ]";
    }

}
