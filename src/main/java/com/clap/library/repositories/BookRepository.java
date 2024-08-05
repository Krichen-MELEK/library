package com.clap.library.repositories;

import com.clap.library.entities.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findBooksByAuthorId(UUID id);
    @Modifying
    @Transactional
    @Query("delete from Book where author.id = :id")
    int deleteAllBookByIdList(@Param("id") UUID id);
}
