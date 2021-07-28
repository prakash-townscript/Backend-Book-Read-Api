package com.townscript.goodreadsapi.controller;

import com.townscript.goodreadsapi.dto.ReviewDto;
import com.townscript.goodreadsapi.model.Book;
import com.townscript.goodreadsapi.model.Review;
import com.townscript.goodreadsapi.service.ReviewService;
import com.townscript.goodreadsapi.util.ApiResponse;
import com.townscript.goodreadsapi.util.ResponseBuilder;
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
    public ApiResponse createReview(@RequestBody ReviewDto reviewDto){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            Object data = reviewService.save(reviewDto);
            return responseBuilder.createSuccessResponse(data);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }

    @GetMapping
    public ApiResponse findReviewById(@Param("id") Long id){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            Object data = reviewService.findReviewByBookId(id);
            return responseBuilder.createSuccessResponse(data);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }

}
