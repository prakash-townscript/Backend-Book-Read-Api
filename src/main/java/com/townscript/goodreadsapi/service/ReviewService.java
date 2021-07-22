package com.townscript.goodreadsapi.service;

import com.townscript.goodreadsapi.Repository.ReviewRepository;
import com.townscript.goodreadsapi.dto.ReviewDto;
import com.townscript.goodreadsapi.model.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review save(ReviewDto reviewDto){
        Review review  = mapDtoToReview(reviewDto);
        Review saved = reviewRepository.save(review);
        return saved;
    }

    @Transactional
    public List<Object> findReviewByBookId(Long id){
        return reviewRepository.findReviewByBookId(id);
    }

    private Review mapDtoToReview(ReviewDto reviewDto) {
        return Review.builder()
                .bookId(reviewDto.getBookId())
                .userId(reviewDto.getUserId())
                .review(reviewDto.getReview())
                .publishDate(Instant.now())
                .build();
    }

}
