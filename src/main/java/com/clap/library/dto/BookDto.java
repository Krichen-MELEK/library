package com.clap.library.dto;

import com.clap.library.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private UUID id;
    private String title;
    private Double price;
    private AuthorDto author;
    private Date publicationDate;
}
