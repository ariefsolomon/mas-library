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
        SystemHeader.showHeader("Reader", SystemConstants.WIDTH, 0);
        librarianLoop:
        while (true) {
            SystemHeader.showSubHeader("Reader Menu", SystemConstants.WIDTH);
            System.out.println("1. See books" +
                    "\n2. Borrow book" +
                    "\n3. Return book" +
                    "\n4. Back");
            System.out.print("Select: ");
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
