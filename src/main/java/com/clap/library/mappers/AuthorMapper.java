package com.clap.library.mappers;

import com.clap.library.dto.AuthorDto;
import com.clap.library.entities.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements EntityMapper<Author,AuthorDto>{
    public AuthorDto toDto(Author author){
        if(author == null) return null;
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName());
    }
    public Author toEntity(AuthorDto authorDto){
        if(authorDto == null) return null;
        return new Author(authorDto.getId(), authorDto.getFirstName(), authorDto.getLastName());
    }
}
