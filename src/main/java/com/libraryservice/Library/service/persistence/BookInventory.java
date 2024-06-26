/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libraryservice.Library.service.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @author gajit
 */
@Entity
@Table(name = "book_inventory")
@Data
public class BookInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inventory_id")
    private Integer inventoryId;
    @Column(name = "available")
    private Boolean available;
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    @ManyToOne
    private Book bookId;

    public BookInventory() {
    }

    public BookInventory(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventoryId != null ? inventoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookInventory)) {
            return false;
        }
        BookInventory other = (BookInventory) object;
        if ((this.inventoryId == null && other.inventoryId != null) || (this.inventoryId != null && !this.inventoryId.equals(other.inventoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.resources.jpa.BookInventory[ inventoryId=" + inventoryId + " ]";
    }

}
