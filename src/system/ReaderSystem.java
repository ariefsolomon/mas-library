package system;

import model.User;
import util.InputHelper;
import util.SystemConstants;

public class ReaderSystem {
    private User currentUser;

    public ReaderSystem(User currentUser) {
        this.currentUser = currentUser;
    }

    public void start() {
        header();
        librarianLoop:
        while (true) {
            menuHeader();
            System.out.println("Pilih menu: " +
                    "\n1. Lihat daftar buku tersedia" +
                    "\n2. Pinjam buku" +
                    "\n3. Kembalikan buku" +
                    "\n4. keluar");
            System.out.print("Pilihan: ");
            int chosen = InputHelper.getInputInterval(1, 4);
            if (chosen == -1) {
                System.out.println("\n" + SystemConstants.PREFIX_INVALID_INPUT + "Pilih dengan angka 1 sampai 4!");
                continue;
            }
            switch (chosen) {
                case 4:
                    break librarianLoop;
            }
        }
    }

    private void header() {
        System.out.println("\n========================================");
        System.out.println("|             Menu Reader              |");
        System.out.println("========================================");
    }

    private void menuHeader() {
        System.out.println("\n-------------- Reader Menu -------------");
    }
}
