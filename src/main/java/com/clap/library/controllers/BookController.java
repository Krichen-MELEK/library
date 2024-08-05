package com.clap.library.controllers;

import com.clap.library.dto.BookDto;
import com.clap.library.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/add")
    public BookDto addBook(@RequestBody BookDto bookDto){
        return bookService.addBook(bookDto);
    }
    @GetMapping("/get/all")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/get/{id}")
    public BookDto getBookById(@PathVariable UUID id){
        return bookService.getBookById(id);
    }
    @GetMapping("/get/author/{id}")
    public List<BookDto> getBookListByAuthorId(@PathVariable UUID id){
        return bookService.getBookListByAuthorId(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteBookById(@PathVariable UUID id){
        bookService.deleteBookById(id);
    }
}
