package com.townscript.goodreadsapi.controller;

import com.townscript.goodreadsapi.dto.BookDto;
import com.townscript.goodreadsapi.model.Book;
import com.townscript.goodreadsapi.service.BookService;
import com.townscript.goodreadsapi.util.ApiResponse;
import com.townscript.goodreadsapi.util.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
@CrossOrigin
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ApiResponse createBook(@RequestBody BookDto bookDto){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            Book book = bookService.save(bookDto);
            return responseBuilder.createSuccessResponse(book);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }

    @GetMapping
    public ApiResponse getAllBooks(){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        List<BookDto> books = bookService.getAll();
        try{
            return responseBuilder.createSuccessResponse(books);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }

    @GetMapping("/{bookId}")
    public ApiResponse findBookById(@PathVariable("bookId") Long id){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            BookDto book = bookService.findBookById(id);
            return responseBuilder.createSuccessResponse(book);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }

    }

    @GetMapping("/library")
    public ApiResponse findBookByBookId(@Param("id") Long id){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            Object data = bookService.findBookByUser(id);
            return responseBuilder.createSuccessResponse(data);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }

    @GetMapping("/filter")
    public ApiResponse findBookByFilter(@Param("id") Long id){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        try{
            Object data = bookService.findBookksByFilter(id);
            return responseBuilder.createSuccessResponse(data);
        }catch (Exception e){
            return responseBuilder.createErrorResponse(e.toString());
        }
    }
}
