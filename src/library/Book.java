package library;
import library.logs.IdChecker;
import library.user.Author;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Book {
    int id;
    String title;
    String author;
    String singUpDate;
    String updateDate;
    boolean available = true;

    public Book(String tittle, String aut) {
        setId();
        this.title = tittle;
        this.author = aut;
        this.singUpDate = getStringDate();
    }

    public Book(String tittle, String aut, String singUpDate) {
        setId();
        this.title = tittle;
        this.author = aut;
        this.singUpDate = singUpDate;
    }

    private String getStringDate() {
        LocalDateTime t = LocalDateTime.now();
        return String.format("%d/%d/%d %d:%d:%d",
                t.getDayOfMonth(),
                t.getMonthValue(),
                t.getYear(),
                t.getHour(),
                t.getMinute(),
                t.getSecond());
    }

    private void setId(){
        IdChecker checker = new IdChecker();
        this.id = checker.returnAvailableId(Book.class);
    }

    public String getName() {
        return this.title;
    }


    public ArrayList<String> displayAttributes() {
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add(String.valueOf(id));
        attributes.add(title);
        attributes.add(author);
        attributes.add(singUpDate);
        attributes.add(String.valueOf(available));

        return attributes;
    }
}


