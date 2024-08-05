package com.clap.library.services;

import com.clap.library.dto.AuthorDto;
import com.clap.library.dto.BookDto;
import com.clap.library.entities.Author;
import com.clap.library.entities.Book;
import com.clap.library.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {
    @MockBean
    BookRepository bookRepository;
    @Autowired
    BookService bookService;

    @Test
    void testAddBook(){
        UUID randomAuthorId = UUID.randomUUID();
        Author author = new Author(randomAuthorId, "First name", "Last name");
        AuthorDto authorDto = new AuthorDto(randomAuthorId, "First name", "Last name");

        UUID randomId = UUID.randomUUID();
        Date publicationDate = new Date();
        BookDto bookDtoBeforeSave = new BookDto(null, "Les contemplations", 40.0 , authorDto, publicationDate);
        BookDto bookDtoAfterSave = new BookDto(randomId, "Les contemplations", 40.0 , authorDto, publicationDate);


        Book bookBeforeSave = new Book(null, "Les contemplations", 40.0 , author, publicationDate);
        Book bookAfterSave = new Book(randomId, "Les contemplations", 40.0 , author, publicationDate);
        when(bookRepository.save(bookBeforeSave)).thenReturn(bookAfterSave);

        BookDto result = bookService.addBook(bookDtoBeforeSave);

        assertEquals("Les contemplations", result.getTitle(), "Should have the correct title");
        assertEquals(40, result.getPrice(), "Should have the correct price");
        assertNotNull(result.getId(), "Should have a new UUID");

        bookDtoAfterSave.setTitle("Les contemplations 2.0");

        bookAfterSave.setTitle("Les contemplations 2.0");
        when(bookRepository.save(bookAfterSave)).thenReturn(bookAfterSave);

        BookDto resultUpdated = bookService.addBook(bookDtoAfterSave);

        assertEquals("Les contemplations 2.0", resultUpdated.getTitle(), "Should have the updated first name");
    }

    @Test
    void testGetAllBooks(){
        UUID randomAuthorId = UUID.randomUUID();
        Author author = new Author(randomAuthorId, "First name", "Last name");

        List<Book> bookList = List.of(
                new Book(UUID.randomUUID(),"First book", 250.0, author, new Date())
        );
        when(bookRepository.findAll()).thenReturn(bookList);

        List<BookDto> result =  bookService.getAllBooks();

        assertEquals(1, result.size(), "Should have 1 book in the database");
    }

    @Test
    void testGetBookById(){
        UUID randomAuthorId = UUID.randomUUID();
        Author author = new Author(randomAuthorId, "First name", "Last name");

        UUID bookId = UUID.randomUUID();
        Book book = new Book(bookId,"First book", 250.0, author, new Date());
        when(bookRepository.getReferenceById(bookId)).thenReturn(book);

        BookDto result =  bookService.getBookById(bookId);

        assertEquals(bookId, result.getId(), "Should have correct book id");
    }

    @Test
    void testGetBookListByAuthorId(){
        UUID randomAuthor1Id = UUID.randomUUID();
        Author author1 = new Author(randomAuthor1Id, "Author1 first  name", "Last name");
        UUID randomAuthor2Id = UUID.randomUUID();
        Author author2 = new Author(randomAuthor2Id, "Author2 first name", "Last name");

        Book book1 = new Book(UUID.randomUUID(),"First book", 250.0, author1, new Date());
        Book book2 = new Book(UUID.randomUUID(),"First book", 250.0, author1, new Date());
        Book book3 = new Book(UUID.randomUUID(),"First book", 250.0, author2, new Date());
        when(bookRepository.findBooksByAuthorId(randomAuthor1Id)).thenReturn(List.of(book1,book2));

        List<BookDto> result = bookService.getBookListByAuthorId(randomAuthor1Id);

        assertEquals(2, result.size(), "Should have 2 books associated to the selected author");
    }


    
}
