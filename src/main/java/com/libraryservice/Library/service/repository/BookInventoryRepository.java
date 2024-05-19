package com.libraryservice.Library.service.repository;

import com.libraryservice.Library.service.persistence.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookInventoryRepository extends JpaRepository<BookInventory, Integer> {

    Optional<List<BookInventory>> findByBookIdBookId(Integer bookId);
}
