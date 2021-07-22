package com.townscript.goodreadsapi.controller;


import com.townscript.goodreadsapi.dto.BookDto;
import com.townscript.goodreadsapi.model.Book;
import com.townscript.goodreadsapi.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
@CrossOrigin
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.save(bookDto));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getAll());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> findBookById(@PathVariable("bookId") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.findBookById(id));
    }

    @GetMapping("/library")
    public ResponseEntity<List<Object> > findBookByBookId(@Param("id") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.findBookByUser(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Object> > findBookByFilter(@Param("id") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.findBookksByFilter(id));
    }
}
