package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void findUser(String model, int series) {
        String HQL = "SELECT DISTINCT object(car) from Car car LEFT JOIN FETCH Car.id WHERE (car.model = ? and car.series = ?)";
        List result = (List) sessionFactory.getCurrentSession().createQuery(HQL).setParameter(0, model).setParameter(1, series);
        System.out.println(result);
    }
}
