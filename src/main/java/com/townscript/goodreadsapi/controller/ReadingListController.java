package com.townscript.goodreadsapi.controller;

import com.townscript.goodreadsapi.dto.ReadingListDto;
import com.townscript.goodreadsapi.model.Book;
import com.townscript.goodreadsapi.model.ReadingList;
import com.townscript.goodreadsapi.service.ReadingListService;
import com.townscript.goodreadsapi.util.ApiResponse;
import com.townscript.goodreadsapi.util.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@RestController
@RequestMapping("api/readinglist")
@AllArgsConstructor
@CrossOrigin
public class ReadingListController {

    private ReadingListService readingListService;

    @PostMapping("/add")
    public ApiResponse createReadingList(@RequestBody ReadingListDto readingListDto){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            ReadingList data = readingListService.save(readingListDto);
            return responseBuilder.createSuccessResponse(data);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }

    @PutMapping()
    @Transactional
    public ApiResponse updateUserReadingList(@Param("userId") Long userId,@Param("bookId") Long bookId){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            Object data  = readingListService.updateUserReadingList(userId,bookId);
            return responseBuilder.createSuccessResponse(data);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }


    @DeleteMapping
    @Transactional
    public ApiResponse deleteFromReadingList(@Param("userId") Long userId,@Param("bookId") Long bookId){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            Object data = readingListService.deleteFromReadingList(userId,bookId);
            return responseBuilder.createSuccessResponse(data);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }

}
