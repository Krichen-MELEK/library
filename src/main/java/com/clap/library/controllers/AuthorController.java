package com.clap.library.controllers;

import com.clap.library.dto.AuthorDto;
import com.clap.library.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/add")
    public AuthorDto addAuthor(@RequestBody AuthorDto authorDto){
        return authorService.addAuthor(authorDto);
    }
    @GetMapping("/get/all")
    public List<AuthorDto> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @GetMapping("/get/{id}")
    public AuthorDto getAuthorById(@PathVariable UUID id){
        return authorService.getAuthorById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAuthorById(@PathVariable UUID id){
        authorService.deleteAuthorById(id);
    }
}
