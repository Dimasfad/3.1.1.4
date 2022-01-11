package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl a = new UserServiceImpl();
        a.createUsersTable();
        a.saveUser("Petr", "Ivanov", (byte) 25);
        a.saveUser("Bob", "Johnson", (byte) 45);
        a.saveUser("John", "Snow", (byte) 36);
        a.saveUser("Steve", "Oldson", (byte) 18);
        a.getAllUsers();
        a.cleanUsersTable();
        a.getAllUsers();
        a.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
