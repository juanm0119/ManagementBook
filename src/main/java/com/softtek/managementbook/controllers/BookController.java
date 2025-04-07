package com.softtek.managementbook.controllers;

import com.softtek.managementbook.dtos.BooksResponse;
import com.softtek.managementbook.dtos.MessageResponse;
import com.softtek.managementbook.entities.Book;
import com.softtek.managementbook.dtos.BookRequest;
import com.softtek.managementbook.services.books.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    @GetMapping
    public ResponseEntity<List<BooksResponse>> getAllBooks(@RequestParam int userId) throws Exception {
        List<BooksResponse> books = this.service.getAll(userId).stream().map(BooksResponse::from).toList();

        return ResponseEntity.status(!books.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksResponse> getBookById(@PathVariable int id) {
        ResponseEntity<BooksResponse> response;
        try {
            Book found = this.service.getById(id);
            response = ResponseEntity.ok().body(BooksResponse.from(found));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> saveBook(@RequestBody BookRequest newBook) {
        ResponseEntity<MessageResponse> response;

        try {
            this.service.create(newBook);
            response = ResponseEntity.ok().body(new MessageResponse("Book created successfully"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> editBook(@PathVariable int id, @RequestBody BookRequest book) {
        ResponseEntity<MessageResponse> response;

        try {
            this.service.update(id, book);
            response = ResponseEntity.ok(new MessageResponse("Book updated successfully"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteBook(@PathVariable int id) {
        ResponseEntity<MessageResponse> response;

        try {
            this.service.delete(id);
            response = ResponseEntity.ok(new MessageResponse("Book deleted successfully"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return response;
    }
}
