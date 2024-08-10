package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl us = new UserServiceImpl();

        us.createUsersTable();

        us.saveUser("Вася", "Первый", (byte) 2);
        us.saveUser("Коля", "Второй", (byte) 4);
        us.saveUser("Олег", "Третий", (byte) 8);
        us.saveUser("Костя", "Четвёртый", (byte) 16);

        List<User> ul = us.getAllUsers();
        for (User u : ul) System.out.println(u);

        us.cleanUsersTable();

        us.dropUsersTable();

    }
}
