import library.Book;
import library.Borrow;
import library.logs.Register;
import library.user.Client;

import java.util.Locale;
import java.util.Scanner;

// Features Criar Clientes, Cadastrar Livros, Fazer Empréstimos de Livros

public class LibraryApp {
    Scanner scanner;
    Register register;
    Borrow borrow;

    public LibraryApp() {
        scanner = new Scanner(System.in).useLocale(Locale.US);
        register = new Register();
        borrow = new Borrow();
    }

    public void launch() {
        boolean online = true;

        System.out.println("Welcome to the LibraryApp.");
        while (online) {
            System.out.println("The commands are: (l) List Books, (b) Borrow Book, (s) Search a specific book."); // criar função para disp comandos
            // System.out.println("") mudar para criar cliente e adicionar livro
            System.out.println("Please, insert you command:");

            String answer = scanner.next();
            if (answer.equalsIgnoreCase("e")) {
                online = false;
            }
            processActions(answer);
        }
    }

    private void processActions(String input) {
        input = input.toLowerCase();
        switch (input) {
            case "l" -> listBooks();
            case "s" -> searchBook();
            case "b" -> borrowBook();
            case "d" -> deliverBookBack();
        }
    }

    private void createClient(Client client) {
    }

    // code for book operations
    private void createBook(Book book) {
        String r = register.searchResults(Book.class, book.getName());
        if (r.isBlank()) {
            register.registerElements(book.displayAttributes(), Book.class);
            return;
        }
        System.out.println("Book already exists.");
    }

    private void listBooks() {
        register.readElements(Book.class);
    }

    private void searchBook() {
        System.out.println("Please send the book name: ");
        scanner.nextLine();
        String search = scanner.nextLine();
        register.searchResults(Book.class, search);
    }

    public void borrowBook() {
        System.out.println("Please, insert the book name: ");
        scanner.nextLine();
        String bookName = scanner.nextLine();

        borrow.borrow(bookName);
    }

    public void deliverBookBack() { // incrementar essa função depois

    }
}

