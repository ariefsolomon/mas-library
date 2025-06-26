package model;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private int year;
    private int pages;
    private String genre;
    private String description;

    private Book(BookBuilder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.isbn = builder.isbn;
        this.publisher = builder.publisher;
        this.year = builder.year;
        this.pages = builder.pages;
        this.genre = builder.genre;
        this.description = builder.description;
    }

    public static class BookBuilder {
        private final String title;
        private final String author;
        private String isbn = "";
        private String publisher = "";
        private int year = 0;
        private int pages = 0;
        private String genre = "";
        private String description = "";

        public BookBuilder(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public BookBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public BookBuilder year(int year) {
            this.year = year;
            return this;
        }

        public BookBuilder pages(int pages) {
            this.pages = pages;
            return this;
        }

        public BookBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public BookBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
