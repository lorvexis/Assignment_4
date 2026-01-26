package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {

    private final Library library = new Library();

    @GetMapping("/books")
    public List<Book> getBooks() {
        return library.getAllBooks();
    }

    @PostMapping("/books")
    public String addBook(@RequestBody Book book) {
        library.addBookToDatabase(book);
        return "Book added";
    }
    @PutMapping("/books/{isbn}")
    public String updateBook(
            @PathVariable String isbn,
            @RequestParam boolean available) {

        library.updateAvailability(isbn, available);
        return "Book updated";
    }
    @DeleteMapping("/books/{isbn}")
    public String deleteBook(@PathVariable String isbn) {
        library.deleteBook(isbn);
        return "Book deleted";
    }


}
