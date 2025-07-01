package system;

import model.Book;
import model.Librarian;
import model.User;
import repository.BookRepository;
import util.InputHelper;
import util.SystemConstants;
import util.SystemHeader;

import java.util.List;

public class LibrarianSystem {
    private Librarian currentUser;
    private BookRepository library;
    private BookRepository bookRepository;

    public LibrarianSystem(User currentUser, BookRepository bookRepository) {
        this.currentUser = (Librarian) currentUser;
        this.library = ((Librarian) currentUser).getLibrary();
        this.bookRepository = bookRepository;
    }

    public void start() {
        SystemHeader.showHeader("Librarian", SystemConstants.WIDTH, 0);
        librarianLoop:
        while (true) {
            SystemHeader.showSubHeader("Librarian Menu", SystemConstants.WIDTH);
            System.out.println("1. Show my books" +
                    "\n2. Show borrowers" +
                    "\n3. Add book" +
                    "\n4. Delete book" +
                    "\n5. Back");
            System.out.print("Select: ");
            int chosen = InputHelper.getInputInterval(1, 5);
            if (chosen == -1) {
                System.out.println("\n" + SystemConstants.PREFIX_INVALID_INPUT + "Pilih dengan angka 1 sampai 4!");
                continue;
            }
            switch (chosen) {
                case 1:
                    showBooks();
                    break;
                case 2:
                    showBorrowers();
                    break;
                case 3:
                    addBookHandler();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    currentUser.getLibrary().saveBooksToCsv(currentUser.getLibraryPath(), SystemConstants.CSV_BOOK_HEADER);
                    bookRepository.saveBooksToCsv(SystemConstants.PATH_CSV_BOOKS, SystemConstants.CSV_BOOK_HEADER);
                    break librarianLoop;
            }
        }
    }

    private void showBooks() {
        BookRepository library = currentUser.getLibrary();
        System.out.println("\n" + "-".repeat(SystemConstants.WIDTH));
        if (library.isEmpty()) {
            System.out.println("Empty");
            return;
        }
        for (Book book : library.getBookRepository()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }
        System.out.println("-".repeat(SystemConstants.WIDTH));
    }

    private void showBorrowers() {

    }

    private void addBookHandler() {
        System.out.print("Title: ");
        String title = InputHelper.getInputString(true);
        System.out.print("Author: ");
        String author = InputHelper.getInputString(true);
        Book book = new Book(title, author);
        library.addBook(book);
        bookRepository.addBook(book);
    }

    private void deleteBook() {

    }
}
