package system;

import model.Book;
import model.Librarian;
import model.User;
import util.InputHelper;
import util.SystemConstants;
import util.SystemHeader;

import java.util.List;

public class LibrarianSystem {
    private Librarian currentUser;

    public LibrarianSystem(User currentUser) {
        this.currentUser = (Librarian) currentUser;
    }

    public void start() {
        SystemHeader.showHeader("Librarian");
        librarianLoop:
        while (true) {
            SystemHeader.showSubHeader("Librarian Menu");
            System.out.println("Pilih menu: " +
                    "\n1. Lihat buku di library saya" +
                    "\n2. Lihat daftar peminjam buku" +
                    "\n3. Tambah buku" +
                    "\n4. Hapus buku" +
                    "\n5. Keluar");
            System.out.print("Pilihan: ");
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
                    addBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    break librarianLoop;
            }
        }
    }

    private void showBooks() {
        List<Book> library = currentUser.getLibrary();
        System.out.println("\n----------------------------------------");
        if (library.isEmpty()) {
            System.out.println("Belum ada buku!");
            return;
        }
        for (Book book : library) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }
        System.out.println("----------------------------------------");
    }

    private void showBorrowers() {

    }

    private void addBook() {

    }

    private void deleteBook() {

    }
}
