package user;

public class UserEditInfo extends User {

    public UserEditInfo(String name, String surname, String email) {             // Czy to dobre rozwiązanie?
        super(null, null, name, surname, email);
    }
}

