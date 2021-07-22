package com.townscript.goodreadsapi.Repository;

import com.townscript.goodreadsapi.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query(value = "Select review.review , user.email from " +
            "review INNER JOIN user ON  " +
            "review.user_id = user.user_id where review.book_id = :id",
            nativeQuery = true)
    List<Object>  findReviewByBookId(@Param("id") Long id);
}
