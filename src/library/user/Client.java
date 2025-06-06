package library.user;
import library.logs.IdChecker;
import java.util.ArrayList;

public class Client extends User{
    private int id;
    private String password; // idealmente deve-se usar um hash

    public Client(String name, String birthday) {
        super(name, birthday);
        setId();
    }

    private void setId() {
        IdChecker checker = new IdChecker();
        this.id = checker.returnAvailableId(Client.class);
    }

    public void setPassword(String password) {
        this.password = password;
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
