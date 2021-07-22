package com.townscript.goodreadsapi.controller;

import com.townscript.goodreadsapi.dto.ReviewDto;
import com.townscript.goodreadsapi.model.Review;
import com.townscript.goodreadsapi.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/book/review")
@AllArgsConstructor
@CrossOrigin
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto reviewDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.save(reviewDto));
    }

    @GetMapping
    public ResponseEntity<List<Object> > findReviewById(@Param("id") Long id){
       return ResponseEntity.status(HttpStatus.OK)
               .body(reviewService.findReviewByBookId(id));
    }

}
