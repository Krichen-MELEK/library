package com.clap.library.mappers;

import com.clap.library.dto.AuthorDto;
import com.clap.library.dto.BookDto;
import com.clap.library.entities.Author;
import com.clap.library.entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper implements EntityMapper<Book, BookDto>{
    private final AuthorMapper authorMapper;
    public BookDto toDto(Book book){
        if(book == null) return null;
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getPrice(),
                authorMapper.toDto(book.getAuthor()),
                book.getPublicationDate());
    }
    public Book toEntity(BookDto bookDto){
        if(bookDto == null) return null;
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getPrice(),
                authorMapper.toEntity(bookDto.getAuthor()),
                bookDto.getPublicationDate());
    }
}
