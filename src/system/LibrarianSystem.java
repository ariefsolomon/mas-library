package system;

import util.InputHelper;
import util.SystemConstants;

public class LibrarianSystem {
    public void start() {
        header();
        librarianLoop:
        while (true) {
            menuHeader();
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
                case 5:
                    break librarianLoop;
            }
        }
    }

    private void header() {
        System.out.println("\n========================================");
        System.out.println("|            Menu Librarian            |");
        System.out.println("========================================");
    }

    private void menuHeader() {
        System.out.println("\n------------- Librarian Menu -----------");
    }
}
