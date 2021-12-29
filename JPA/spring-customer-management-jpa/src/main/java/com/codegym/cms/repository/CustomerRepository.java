package com.codegym.cms.repository;

import com.codegym.cms.model.Customer;



import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Transactional
public class CustomerRepository implements ICustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> searchByAll(String keySearch) {
        String hql;
        List<Customer> searchs = new ArrayList<>();
        for (String str: new String[]{"c.id","c.firstName","c.lastName"}
             ) {
                 hql = "select c from Customer c " +
                        "where "+str+" LIKE '%"+ keySearch +"%'";
            TypedQuery<Customer> query = em.createQuery(hql, Customer.class);

                if (query.getResultList()!= null) {
                    searchs.addAll(query.getResultList());
                }
        }
        for (int i = 0; i < searchs.size()-1; i++) {
            for (int j = i+1; j <searchs.size(); j++) {
                if (searchs.get(i).equals(searchs.get(j))) {
                    searchs.remove(j);
                }
            }
        }


        return searchs;

    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where  c.id=:id", Customer.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId() != null) {
            em.merge(customer);
        } else {
            em.persist(customer);
        }
    }

    @Override
    public void remove(Long id) {
        Customer customer = findById(id);
        if (customer != null) {
            em.remove(customer);
        }
    }


}
