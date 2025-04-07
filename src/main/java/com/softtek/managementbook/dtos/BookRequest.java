package com.softtek.managementbook.dtos;

public record BookRequest(String isbn, String title, String genre, String synopsis, String publishedAt, String createdAt, int userId) {
}
