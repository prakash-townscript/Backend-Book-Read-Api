package com.townscript.goodreadsapi.Repository;

import com.townscript.goodreadsapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "Select book.book_id , book.name , book.description , book.pages ," +
            "book.author ,book.image_url,reading_list.reading_status from " +
            "book INNER JOIN reading_list ON reading_list.book_id = " +
            "book.book_id where reading_list.user_id = :id",
    nativeQuery = true)
    List<Object> findBookByUser(@Param("id") Long id);


    @Query(value = "Select book.book_id , book.name , book.description , book.pages , "+
            "book.author ,book.image_url from "+
            "book where book_id NOT IN ( select book_id from reading_list "+
            "where user_id = :id )"
            ,nativeQuery = true)
    List<Object> findAllBookFilter(@Param("id") Long id);




}
