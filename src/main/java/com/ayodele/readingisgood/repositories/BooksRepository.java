package com.ayodele.readingisgood.repositories;

import com.ayodele.readingisgood.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Integer> {

}
