package user;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserService {

    private Set<User> users = new HashSet<>();

    public void register(User user) {
        if (checkIfUsernameExists(user.getUsername())) { //TODO nazwa np. checkIfUsernameExists // DONE
            System.out.println("Podany login jest już zajęty.");
        } else {
            users.add(user);
            System.out.println("Rejestracja przebiegła pomyślnie.");
        }
    }

    public boolean login(String username, String password) {
        return users.stream()
                .anyMatch(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()));
    }


    public void getUserDetails(String password, String username) {
        if (login(username, password)) {
            users.stream()
                    .filter(user -> username.equals(user.getUsername()))
                    .findAny()
                    .ifPresent(user -> {
                        System.out.println("Dane użytkownika:");
                        System.out.println("Login: " + user.getUsername());
                        System.out.println("Hasło: " + user.getPassword());
                        System.out.println("Imię: " + user.getName());
                        System.out.println("Nazwisko: " + user.getSurname());
                        System.out.println("Email: " + user.getEmail());
                    });

        } else {
            System.out.println("Błędny login lub hasło.");
        }
    }

    public void changeUserDetails(String password, String username, UserEditInfo userEditInfo) { //TODO zmienic // DONE
        if (login(username, password)) {
            users.stream()
                    .filter(user -> username.equals(user.getUsername()))
                    .findAny()
                    .ifPresent(user -> {
                        user.setName(userEditInfo.getName());
                        user.setSurname(userEditInfo.getSurname());
                        user.setEmail(userEditInfo.getEmail());
                        System.out.println("Dane zostały zaktualizowane.");
                    });
        } else {
            System.out.println("Błędny login lub hasło.");
        }

    }

    public void changeUserPassword(String password, String username, String newPassword) { //TODO to samo co wyzej // DONE
        if (login(username, password)) {
            users.stream()
                    .filter(user -> username.equals(user.getUsername()))
                    .findAny()
                    .ifPresent(user -> {
                        user.setPassword(newPassword);
                        System.out.println("Hasło zostało zmienione.");
                    });
        } else {
            System.out.println("Błędny login lub hasło.");
        }
    }


//    public boolean isTheSameUser(User user, String username, String password) { //TODO zostawic stare rozwiazanie w komentarzu i pomyslec jak to zrobic jakos z Optionalem
//        User searchUser = null;
//        if (login(username, password)) {
//            searchUser = users.stream()
//                    .filter(u -> username.equals(u.getUsername()))
//                    .findAny()
//                    .orElse(null);
//        }
//        return user.equals(searchUser);
//    }
//
//    public boolean isTheSameUserDummy(User user, String username, String password) {
//        User searchUser = null;
//        if (login(username, password)) {
//            searchUser = users.stream()
//                    .filter(u -> username == u.getUsername())
//                    .findAny()
//                    .orElse(null);
//
//        }
//        return user == searchUser;
//    }


    public boolean isTheSameUser(User user, String username, String password) {
        if (login(username, password)) {
           Optional<User> findUser = users.stream()
                    .filter(u -> username.equals(u.getUsername()))
                    .findAny();
           return findUser.isPresent() && user.equals(findUser.get());
        }
        return false;
    }

    public boolean isTheSameUserDummy(User user, String username, String password) {
        if (login(username, password)) {
            Optional<User> findUser = users.stream()
                    .filter(u -> username == (u.getUsername()))
                    .findAny();
            return findUser.isPresent() && user == (findUser.get());
        }
        return false;
    }
    public void deleteUser(String username, String password) { //TODO zlaczyc /// done
        if (login(username, password)) {
            users.removeIf(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()));
                        System.out.println("Użytkownik usunięty");
        } else {
            System.out.println("Nie znaleziono takiego użytkownika");
        }
    }

    public void deleteUserDummy(User user) {
        if (users.remove(user)) {
            System.out.println("Użytkownik usunięty");
        } else {
            System.out.println("Nie znaleziono użytkownika");
        }
    }

    private boolean checkIfUsernameExists(String username) {
        return users.stream()
                .anyMatch(user -> username.equals(user.getUsername()));

    }
}




