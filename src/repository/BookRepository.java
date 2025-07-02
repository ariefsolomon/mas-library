package repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import model.Book;
import util.CsvUtils;
import util.SystemConstants;

public class BookRepository {
    private List<Book> bookRepository;

    public BookRepository(String path) {
        File csvFile = new File(path);
        if (!csvFile.exists()) {
            CsvUtils.writeCSV(path, new String[]{}, false);
        }
        bookRepository = loadBooksFromCsv(path, true);
    }

    public Book getBook(String title, String author) {
        return bookRepository.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title) &&
                        book.getAuthor().equalsIgnoreCase(author))
                .findFirst()
                .orElse(null);
    }

    public void addBook(Book book) {
        bookRepository.add(book);
    }

    public void removeBook(Book book) {
        bookRepository.remove(book);
    }

    public boolean isEmpty() {
        return bookRepository.isEmpty();
    }

    public List<Book> getBookRepository() {
        return bookRepository;
    }

    public List<Book> loadBooksFromCsv(String path, boolean header) {
        List<Book> result = new ArrayList<>();
        File csvFile = new File(path);
        if (!csvFile.exists()) {
            return result;
        }
        List<String[]> data = CsvUtils.readCSV(path);
        for (String[] row : data) {
            if (header) {
                header = false;
                continue;
            }
            if (row.length >= 2) {
                Book book = new Book(row[0], row[1]);
                result.add(book);
            }
        }
        return result;
    }

    public void saveBooksToCsv(String path, String[] header) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeNext(header);
            for (Book book : bookRepository) {
                String[] data = {book.getTitle(), book.getAuthor()};
                writer.writeNext(data);
            }
        } catch (IOException e) {
            System.err.println(SystemConstants.PREFIX_FAILED + e.getMessage());
        }
    }
}
