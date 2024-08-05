package com.clap.library.services;

import com.clap.library.dto.AuthorDto;
import com.clap.library.entities.Author;
import com.clap.library.mappers.AuthorMapper;
import com.clap.library.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthorServiceTest {

    @MockBean
    AuthorRepository authorRepository;
    @Autowired
    AuthorService authorService;

    @Test
    void testAddAuthor(){
        UUID randomId = UUID.randomUUID();
        AuthorDto authorDtoBeforeSave = new AuthorDto(null, "First name", "Last name");
        AuthorDto authorDtoAfterSave = new AuthorDto(randomId, "First name", "Last name");

        Author authorEntityBeforeSave = new Author(null, "First name", "Last name");
        Author authorEntityAfterSave = new Author(randomId, "First name", "Last name");
        when(authorRepository.save(authorEntityBeforeSave)).thenReturn(authorEntityAfterSave);

        AuthorDto result = authorService.addAuthor(authorDtoBeforeSave);

        assertEquals("First name", result.getFirstName(), "Should have the correct first name");
        assertEquals("Last name", result.getLastName(), "Should have the correct last name");
        assertNotNull(result.getId(), "Should generate a new UUID");

        authorDtoAfterSave.setFirstName("New first name");
        authorEntityAfterSave.setFirstName("New first name");

        when(authorRepository.save(authorEntityAfterSave)).thenReturn(authorEntityAfterSave);

        AuthorDto resultUpdated = authorService.addAuthor(authorDtoAfterSave);

        assertEquals("New first name", resultUpdated.getFirstName(), "Should have the updated first name");
    }

    @Test
    void testGetAllAuthors(){
        List<Author> authorList = List.of(
                new Author(UUID.randomUUID(),"Victor", "Hugo"),
                new Author(UUID.randomUUID(), "François", "René de Chateaubriand"),
                new Author(UUID.randomUUID(), "Alfred", "de Vigny")
        );

        when(authorRepository.findAll()).thenReturn(authorList);

        List<AuthorDto> result =  authorService.getAllAuthors();

        assertEquals(3, result.size(), "Should have a list of 2 items");
    }

    @Test
    void testGetAuthorById(){
        UUID randomId = UUID.randomUUID();
        Author author =  new Author(randomId,"Victor", "Hugo");
        when(authorRepository.getReferenceById(randomId)).thenReturn(author);

        AuthorDto result =  authorService.getAuthorById(randomId);

        assertEquals(randomId, result.getId(), "Should have the correct id");
        assertEquals("Victor", result.getFirstName(), "Should have the correct first name");
        assertEquals("Hugo", result.getLastName(), "Should have the correct last name");
    }
}
