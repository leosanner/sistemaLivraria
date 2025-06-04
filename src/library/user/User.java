package library.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class User {
    protected String name;
    protected LocalDate birthday;

    public User(String name, String birthday) {
        this.name = name;
        setBirthday(birthday);
    }

    public User() {}

    protected void setBirthday(String birthday) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.birthday = LocalDate.parse(birthday, parser);
    }
}
