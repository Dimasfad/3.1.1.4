package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement stm = Util.uConnection().createStatement()) {
            stm.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), last_name VARCHAR(45), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (PreparedStatement pstm = Util.uConnection().prepareStatement("DROP TABLE IF EXISTS users")) {
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (PreparedStatement pstm = Util.uConnection().prepareStatement("INSERT INTO users (name, last_name, Age) VALUES (?, ?, ?)")) {
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getLastName());
            pstm.setInt(3, user.getAge());
            pstm.executeUpdate();
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement pstm = Util.uConnection().prepareStatement("DELETE FROM users WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement stm = Util.uConnection().createStatement();
             ResultSet resultSet = stm.executeQuery("SELECT * FROM users ")){
            while (resultSet.next()) {
                User u = new User();
                u.setId(resultSet.getLong("id"));
                u.setName(resultSet.getString("name"));
                u.setLastName(resultSet.getString("last_name"));
                u.setAge(resultSet.getByte("age"));
                list.add(u);
            }
            System.out.println(Arrays.toString(list.toArray()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement stm = Util.uConnection().createStatement()) {
            stm.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
