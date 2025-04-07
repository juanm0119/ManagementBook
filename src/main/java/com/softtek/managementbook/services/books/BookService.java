package com.softtek.managementbook.services.books;

import com.softtek.managementbook.entities.Book;
import com.softtek.managementbook.repositories.BookRepository;
import com.softtek.managementbook.repositories.UserRepository;
import com.softtek.managementbook.dtos.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public List<Book> getAll(int userId) throws Exception {
        return bookRepository.getBooksByUserID(userId);
    }

    public Book getById(int id) throws Exception {
        Optional<Book> found = bookRepository.findById(id);

        if (found.isEmpty()) {
            throw new Exception("not found");
        }

        return found.get();
    }

    public Book create(BookRequest bookRequest) throws Exception {
        Book.BookBuilder builder = Book.builder();

        if (bookRequest.isbn().isEmpty()) {
            throw new Exception("isbn is empty");
        }

        if (bookRequest.title().isEmpty()) {
            throw new Exception("title is empty");
        }

        if (bookRequest.genre().isEmpty()) {
            throw new Exception("genre is empty");
        }

        if (bookRequest.synopsis().isEmpty()) {
            throw new Exception("synopsis is empty");
        }

        if (bookRequest.createdAt().isEmpty()) {

            throw new Exception("createdAt is empty");
        }

        if (bookRequest.publishedAt().isEmpty()) {
            throw new Exception("publishedAt is empty");
        }


        builder.isbn(bookRequest.isbn())
                .title(bookRequest.title())
                .synopsis(bookRequest.synopsis())
                .genre(bookRequest.genre())
                .createdAt(Date.valueOf(bookRequest.createdAt()))
                .publishedAt(Date.valueOf(bookRequest.publishedAt()))
                .user(userRepository.findById(bookRequest.userId()).orElse(null));

        System.out.println("aqui");

        return bookRepository.save(builder.build());
    }

    public Book update(int id, BookRequest bookRequest) throws Exception {
        Book book;
        Optional<Book> found = bookRepository.findById(id);

        if (found.isEmpty()) {
            throw new Exception("not found");
        }

        book = found.get();
        book.setIsbn(bookRequest.isbn());
        book.setTitle(bookRequest.title());
        book.setGenre(bookRequest.genre());
        book.setSynopsis(bookRequest.synopsis());
        book.setCreatedAt(Date.valueOf(bookRequest.createdAt()));
        book.setPublishedAt(Date.valueOf(bookRequest.publishedAt()));

        return bookRepository.save(book);
    }

    public boolean delete(int id) throws Exception {
        Optional<Book> found = bookRepository.findById(id);

        if (found.isEmpty()) {
            throw new Exception("not found");
        }

        bookRepository.delete(found.get());

        return true;
    }

}
