package library.user;
import library.logs.IdChecker;
import java.util.ArrayList;

public class Author extends User{
    private int id;

    public Author(String name, String birthday) {
        super(name, birthday);
        setId();
    }

    private void setId(){
        IdChecker checker = new IdChecker();
        this.id = checker.returnAvailableId(Author.class);
    }

    public ArrayList<String> displayAttributes() {
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add(String.valueOf(id));
        attributes.add(name);
        attributes.add(String.valueOf(birthday));

        return attributes;
    }

    public String getName() {
        return this.name;
    }
}
