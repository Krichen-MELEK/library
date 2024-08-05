package com.clap.library.services;

import com.clap.library.dto.AuthorDto;
import com.clap.library.dto.BookDto;
import com.clap.library.mappers.AuthorMapper;
import com.clap.library.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookService bookService;
    private final AuthorMapper authorMapper;
    public AuthorDto addAuthor(AuthorDto authorDto){
        return authorMapper.toDto(authorRepository.save(authorMapper.toEntity(authorDto)));
    }
    public List<AuthorDto> getAllAuthors() {
        return authorMapper.toDtoList(authorRepository.findAll());
    }
    public AuthorDto getAuthorById(UUID id) {
        return authorMapper.toDto(authorRepository.getReferenceById(id));
    }
    @Transactional
    public void deleteAuthorById(UUID id) {
        bookService.deleteBookListByAuthorId(id);
        authorRepository.deleteById(id);
    }
}
