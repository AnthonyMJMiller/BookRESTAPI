package com.javaAPI.RESTAPIPROJECT.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.javaAPI.RESTAPIPROJECT.Exception.BookNotFoundException;
import com.javaAPI.RESTAPIPROJECT.Model.Book;
import com.javaAPI.RESTAPIPROJECT.Repo.BookRepo;

@Controller
public class BookController {
	
	@Autowired
    BookRepo bookRepository;
// Get All Notes
    @GetMapping("/books")
    public List<Book> getAllNotes() {
        return bookRepository.findAll();
    }
// Create a new Note
    @PostMapping("/books")
    public Book createNote(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }
// Get a Single Note
    @GetMapping("/books/{id}")
    public Book getNoteById(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }
// Update a Note
    @PutMapping("/books/{id}")
    public Book updateNote(@PathVariable(value = "id") Long bookId,
                           @Valid @RequestBody Book bookDetails) throws BookNotFoundException {
Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
book.setBook_name(bookDetails.getBook_name());
        book.setAuthor_name(bookDetails.getAuthor_name());
        book.setIsbn(bookDetails.getIsbn());
Book updatedBook = bookRepository.save(book);
return updatedBook;
    }
// Delete a Note
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
bookRepository.delete(book);
return ResponseEntity.ok().build();
    }

}
