package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();

            try {
                session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                        "(id SERIAL PRIMARY KEY, " +
                        " name VARCHAR(255), " +
                        " last_name VARCHAR(255), " +
                        " age INTEGER)");
                transaction.commit();
                System.out.println("Создать таблицу");

            } catch (Exception e){
                if (transaction != null)
                    transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();

            try {
                session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
                transaction.commit();
                System.out.println("Удалить таблицу");

            } catch (Exception e){
                if (transaction != null)
                    transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();

            try {
                session.save(new User(name, lastName, age));
                transaction.commit();
                System.out.println("Добавил");

            } catch (Exception e){
                if (transaction != null)
                    transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {

        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();

            try {
                session.delete(session.get(User.class, id));
                transaction.commit();
                System.out.println("Удалил");

            } catch (Exception e){
                if (transaction != null)
                    transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                Query<User> query = session.createQuery("FROM User", User.class);

                users = query.getResultList();

                transaction.commit();
                System.out.println("Получены пользователи");

            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
                transaction.commit();
                System.out.println("Таблица очищена");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
