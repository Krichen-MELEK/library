package com.clap.library.controllers;

import com.clap.library.entities.Author;
import com.clap.library.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorIt extends AbstractIntegrationTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void deleteAuthorById() {

        List<Author> authorList = List.of(
                new Author(UUID.randomUUID(), "Victor", "Hugo"),
                new Author(UUID.randomUUID(), "François", "René de Chateaubriand"),
                new Author(UUID.randomUUID(), "Alfred", "de Vigny")
        );

        List<Author> savedAuthorList = authorRepository.saveAll(authorList);


        when()
                .request("delete","/author/delete/"+savedAuthorList.get(0).getId())
                .then()
                .statusCode(200);

        List<Author> authorListInDb = new ArrayList<>();
        assertEquals(2, authorRepository.findAll().size());
    }

}
