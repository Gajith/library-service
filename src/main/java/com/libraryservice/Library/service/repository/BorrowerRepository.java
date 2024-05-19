package com.libraryservice.Library.service.repository;

import com.libraryservice.Library.service.persistence.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {
}
