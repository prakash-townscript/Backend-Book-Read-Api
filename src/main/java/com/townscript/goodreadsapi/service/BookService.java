package com.townscript.goodreadsapi.service;


import com.townscript.goodreadsapi.Repository.BookRepository;
import com.townscript.goodreadsapi.dto.BookDto;
import com.townscript.goodreadsapi.exception.SpringCustomException;
import com.townscript.goodreadsapi.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book save(BookDto bookDto){
       Book book  = mapDtoToBook(bookDto);
       Book saved = bookRepository.save(book);
       return saved;
    }

    @Transactional
    public List<BookDto> getAll(){
        return bookRepository.findAll()
                .stream()
                .map(this::mapBookToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookDto findBookById(Long id){
        Book book =  bookRepository.findById(id)
                .orElseThrow(()-> new SpringCustomException("Book by this id doesnt exist"));
        BookDto bookDto = mapBookToDto(book);
        return bookDto;
    }

    @Transactional
    public List<Object> findBookByUser(Long id){
        return bookRepository.findBookByUser(id);
    }

    @Transactional
    public List<Object> findBookksByFilter(Long id){
        return bookRepository.findAllBookFilter(id);
    }

    private BookDto mapBookToDto(Book book){
        return BookDto.builder()
                .bookId(book.getBookId())
                .name(book.getName())
                .image(book.getImageUrl())
                .author(book.getAuthor())
                .pages(book.getPages())
                .description(book.getDescription())
                .build();
    }

    private Book mapDtoToBook(BookDto bookDto) {
       return Book.builder().name(bookDto.getName())
                .name(bookDto.getName())
                .author(bookDto.getAuthor())
                .addedDate(Instant.now())
                .pages(bookDto.getPages())
                .imageUrl(bookDto.getImage())
                .description(bookDto.getDescription())
                .build();
    }


}
