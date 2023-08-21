package user;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService();

        User user1 = new User("akowalska", "U1u1@", "Agnieszka", "Kowalska", "agnieszka.kowalska@gmail.com");
        User user2 = new User("akowalska", "U2u2@", "Aneta", "Kowalski", "aneta.kowalska@gmail.com");
        User user3 = new User("mnowak", "U3u3@", "Mariusz", "Nowak",  "mariusz.nowak@gmail.com");
        User user4 = new User("zwiecek", "U4u4@", "Zbigniew", "Wiecek",  "zbigniew.wiecek@gmail.com");


        userService.register(user1);
        userService.register(user2);
        userService.register(user3);
        userService.register(user4);
        System.out.println("--------------------------------------");

        System.out.println(userService.login("akowalska", "U1u1@"));
        System.out.println(userService.login("akowalska", "U1u12"));

        System.out.println("---------------------------------------");

        userService.getUserDetails("U1u1@", "akowalska");
        System.out.println("---------------------------------------");
        userService.getUserDetails("U1u1@", "akowal");

        System.out.println("---------------------------------------");

        UserEditInfo user1EditInfo = new UserEditInfo("Mario","Kowalski", "mario.kowalski@gmail.com" );
        userService.changeUserDetails("U3u3@","mnowak", user1EditInfo);
        // sprawdzenie
        userService.getUserDetails("U3u3@", "mnowak");

        System.out.println("---------------------------------------");
        userService.changeUserPassword("U3u3@", "mnowak", "U3333");
        userService.changeUserPassword("U3u3", "mnowak", "U3333");

        System.out.println("---------------------------------------");

        System.out.println(userService.isTheSameUser(user1, "akowalska", "U1u1@"));
        System.out.println(userService.isTheSameUser(user2, "akowalska", "U1u1@"));


        System.out.println(userService.isTheSameUserDummy(user2, "akowalska", "U1u1@"));
        System.out.println(userService.isTheSameUserDummy(user2, "akowalska", "U1u1@"));

        System.out.println("---------------------------------------");

        userService.deleteUser("zwiecek", "U4u4@");
        userService.deleteUser("zwiecek", "U4u4");

        userService.deleteUserDummy(user4);
        userService.deleteUserDummy(user1);

    }
}
