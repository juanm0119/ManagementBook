package com.softtek.managementbook.dtos;

import com.softtek.managementbook.entities.Book;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public record BooksResponse(int id, String title, String genre, String synopsis, String isbn, String createdAt, String publishedAt) {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static BooksResponse from(Book book) {

        return new BooksResponse(book.getId(), book.getTitle(), book.getGenre(), book.getSynopsis(), book.getIsbn(), formatter.format(book.getCreatedAt()), formatter.format(book.getPublishedAt()));
    }
}
