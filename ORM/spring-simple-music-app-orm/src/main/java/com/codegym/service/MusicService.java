package com.codegym.service;

import com.codegym.model.Music;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class MusicService implements IMusicService {

    private List<Music> musicList = new ArrayList<>();
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
    public List<Music> findAll() {
        String queryStr = "SELECT m FROM Music AS m";
        TypedQuery<Music> query = entityManager.createQuery(queryStr, Music.class);
        return query.getResultList();
    }

    @Override
    public Music save(Music music) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Music origin;
            if (music.getId()!=0){
                origin = findById(music.getId());
                origin.setName(music.getName());
                origin.setStyle(music.getStyle());
                origin.setSinger(music.getSinger());
                origin.setFilePath(music.getFilePath());
            }
            else{
                origin = music;
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
    public Music findById(int id) {
        String queryStr = "SELECT m FROM Music AS m WHERE m.id = :id";
        TypedQuery<Music> query = entityManager.createQuery(queryStr, Music.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(int id, Music music) {
        for (Music m : musicList) {
            if (m.getId() == id) {
                m = music;
                break;
            }
        }
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < musicList.size(); i++) {
            if (musicList.get(i).getId() == id) {
                musicList.remove(i);
                break;
            }
        }
    }
}
