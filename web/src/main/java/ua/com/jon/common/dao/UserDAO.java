package ua.com.jon.common.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ua.com.jon.common.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Date: 01.01.13
 * Time: 23:58
 */
public class UserDAO implements UserDAOInterface {
    private static Logger log = Logger.getLogger(UserDAO.class.getName());
    private User user;
    private SessionFactory sessionFactory;

    public UserDAO() {
        // TODO inject
        //sessionFactory = HibernateUtils.getSessionFactory();
    }


    @Override
    public boolean insert(User user) {

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction().begin();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            log.severe(e.getMessage());
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            try {
                if (session != null) session.close();
            } catch (SessionException e) {
                log.severe(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public User getUser(String nickname, String eMail) {
        user = new User();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
//            User user = (User)session.get(User.class, 1l);
//            Hibernate.initialize(user.getDeps());
            List<User> users =  session.createCriteria(User.class).add(Restrictions.and(Restrictions.eq("nickName", nickname), Restrictions.eq("eMail", eMail))).list();
            if(users.isEmpty()) return null;
            else return users.get(0);
        } catch (HibernateException e) {
            log.severe(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (session != null) session.close();
            } catch (SessionException e) {
                log.severe(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean getById(Long id) {
        return false;
    }

    @Override
    public boolean getUserByParam(String nickname, String login, String password, String eMail) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public Collection<User> getAllUsers() {
        return null;
    }
}
