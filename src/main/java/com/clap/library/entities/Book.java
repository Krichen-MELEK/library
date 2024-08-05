package com.clap.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private Date publicationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id)
                && Objects.equals(title, book.title)
                && Objects.equals(price, book.price)
                && Objects.equals(author, book.author)
                && Objects.equals(publicationDate, book.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, author, publicationDate);
    }
}
