package library;
import library.logs.Register;

// criar registro para empréstimo -> usuário que pegou, título e hora;

public class Borrow {
    Register register;

    public Borrow() {
        register = new Register();
    }

    public void borrow (String bookName) {
        boolean v = verifyAvailable(bookName);
        if (v) {
            System.out.println("Success Operation.");
            return;
        }
        System.out.println("The current book is not available.");
    }

    public void deliverBack(String bookName) {
        boolean v = verifyAvailable(bookName);
        if (!v) {
            String result = register.searchResults(Book.class, bookName);
            String[] result_split = result.split(",");
            register.changeElementInformation(Book.class, Integer.parseInt(result_split[0]), 4, "true");
            return;
        }
        System.out.println("This book is not borrowed.");
    }

    private void changeAvailableRegister(int id) {
        register.changeElementInformation(Book.class, id, 4, "false");
    }

    private boolean verifyAvailable(String bookName) {
        String result = register.searchResults(Book.class, bookName);
        String[] result_split = result.split(",");
        boolean available = Boolean.parseBoolean(result_split[result_split.length - 1]);

        if (available) {
            changeAvailableRegister(Integer.parseInt(result_split[0]));
        }
        return available;
    }
}
