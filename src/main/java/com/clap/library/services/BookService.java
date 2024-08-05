package com.clap.library.services;

import com.clap.library.dto.BookDto;
import com.clap.library.mappers.BookMapper;
import com.clap.library.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    public BookDto addBook(BookDto bookDto){
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookDto)));
    }
    public List<BookDto> getAllBooks() {
        return bookMapper.toDtoList(bookRepository.findAll());
    }
    public BookDto getBookById(UUID id) {
        return bookMapper.toDto(bookRepository.getReferenceById(id));
    }
    public void deleteBookById(UUID id) {
        bookRepository.deleteById(id);
    }

    public void deleteBookListByAuthorId(UUID authorId) {
        bookRepository.deleteAllBookByIdList(authorId);
    }

    public List<BookDto> getBookListByAuthorId(UUID id) {
        return bookMapper.toDtoList(bookRepository.findBooksByAuthorId(id));
    }
}
