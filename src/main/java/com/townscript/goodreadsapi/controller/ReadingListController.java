package com.townscript.goodreadsapi.controller;

import com.townscript.goodreadsapi.dto.ReadingListDto;
import com.townscript.goodreadsapi.model.ReadingList;
import com.townscript.goodreadsapi.service.ReadingListService;
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
    public ResponseEntity<ReadingList> createReadingList(@RequestBody ReadingListDto readingListDto){
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(readingListService.save(readingListDto));
    }

    @PutMapping()
    @Transactional
    public void updateUserReadingList(@Param("userId") Long userId,@Param("bookId") Long bookId){
     readingListService.updateUserReadingList(userId,bookId);
    }

    @DeleteMapping
    @Transactional
    public void deleteFromReadingList(@Param("userId") Long userId,@Param("bookId") Long bookId){
        readingListService.deleteFromReadingList(userId,bookId);
    }

}
