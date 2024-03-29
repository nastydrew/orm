package com.gymfox.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.gymfox.entities.Entity.setDatabase;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, Entity.InvalidIdException {
        initDatabase();
        User user = new User(-20);
        user.setName("test");
        user.setEmail("test");
        user.save();
        printAllUsers();
    }

    private static void printAllUsers() {
        for (User user : User.all()) {
            user.getName();
            for ( Comment comment : user.getComments() ) {
                System.out.println(String.format("%s : %s - %s", user.getId(), user.getName(), comment.getText()));
            }
        }
    }

    private static void initDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/orm", "postgres",
                    "13121993");
        setDatabase(connection);
    }
}
