import library.Borrow;
import library.logs.Register;
import library.user.Author;
import library.user.Client;
import library.Book;


public class Main {
    public static void main(String[] args) {
//        Client aut = new Client("John", "23/02/2004");
//        Author aut2 = new Author("Pedro", "24/02/2000");
//
//        Register register = new Register();
//        register.registerElements(aut2.displayAttributes(), Client.class);
//        register.registerElements(aut.displayAttributes(), Author.class);
//
//        Book book1 = new Book("Livro do Pedrinho", aut2);
//
////        register.registerElements(book1.displayAttributes(), Book.class);
////        String search = register.searchResults(Author.class, "John");
////        System.out.println(search);
//
////        Borrow borrow = new Borrow();
////        borrow.borrow(book1, aut);
//
//        register.changeElementInformation(Book.class, 2, 4, "false");

        LibraryApp app = new LibraryApp();
        app.launch();
    }
}