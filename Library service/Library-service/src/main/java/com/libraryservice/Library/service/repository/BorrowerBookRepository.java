package com.libraryservice.Library.service.repository;

import com.libraryservice.Library.service.persistence.BorrowerBook;
import com.libraryservice.Library.service.persistence.BorrowerBookPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerBookRepository extends JpaRepository<BorrowerBook, BorrowerBookPK> {
}
