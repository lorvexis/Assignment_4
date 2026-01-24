package com.example.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final Library library = new Library();

    @GetMapping("/books")
    public List<Book> getBooks() {
        return library.getAllBooks();
    }
}
