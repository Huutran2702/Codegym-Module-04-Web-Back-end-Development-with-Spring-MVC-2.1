package cg.wbd.grandemonstration.service;

import cg.wbd.grandemonstration.model.Customer;
import javafx.scene.layout.BorderImage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class HibernateCustomerServiceImpl implements CustomerService{
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Customer> findAll() {
        String queryStr = "SELECT c FROM Customer AS c";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findOne(Long id) {
        String queryStr = "SELECT c FROM Customer AS c WHERE c.id = :id";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Customer save(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Customer origin;
            if (customer.getId()!=null){
             origin = findOne(customer.getId());
                origin.setName(customer.getName());
                origin.setEmail(customer.getEmail());
                origin.setAddress(customer.getAddress());
            }
            else{
             origin = customer;
            }
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }


    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public List<Customer> findAll(List<Long> ids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Customer customer = findOne(id);
            if (customer!=null){
               session.delete(customer);
               transaction.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Customer customer) {


    }

    @Override
    public void delete(List<Customer> customers) {

    }

    @Override
    public void deleteAll() {

    }
}
