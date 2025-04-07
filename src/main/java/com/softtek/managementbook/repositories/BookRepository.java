package com.softtek.managementbook.repositories;

import com.softtek.managementbook.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

  @Query("SELECT b FROM Book b WHERE b.user.id = :userId")
  List<Book> getBooksByUserID(int userId);
}