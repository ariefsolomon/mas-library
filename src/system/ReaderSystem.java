package system;

import model.User;
import util.InputHelper;
import util.SystemConstants;
import util.SystemHeader;

public class ReaderSystem {
    private User currentUser;

    public ReaderSystem(User currentUser) {
        this.currentUser = currentUser;
    }

    public void start() {
        SystemHeader.showHeader("Reader");
        librarianLoop:
        while (true) {
            SystemHeader.showSubHeader("Reader Menu");
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
                case 1:
                case 2:
                case 3:
                case 4:
                    break librarianLoop;
            }
        }
    }
}
