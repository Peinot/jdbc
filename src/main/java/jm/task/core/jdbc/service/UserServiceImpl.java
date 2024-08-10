package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDaoJDBCImpl ud = new UserDaoJDBCImpl();
    private static final UserDaoHibernateImpl uh = new UserDaoHibernateImpl();

    public void createUsersTable() {
        uh.createUsersTable();
    }

    public UserServiceImpl() {

    }

    public void dropUsersTable() {
        uh.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        if(name != null && lastName != null && age >= 0) {
            uh.saveUser(name, lastName, age);
        }
        else System.out.println("Неверные данные");
    }

    public void removeUserById(long id) {
        if (id > 0) uh.removeUserById(id);
        else System.out.println("Неверный id");
    }

    public List<User> getAllUsers() {
        return uh.getAllUsers();
    }

    public void cleanUsersTable() {
        uh.cleanUsersTable();
    }
}
