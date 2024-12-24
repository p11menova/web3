package com.example.wweb.managers;

import com.example.wweb.models.Point;
import com.example.wweb.service.PersistenceManager;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named("dbManagerBean")
@ApplicationScoped
public class DBManagerBean implements Serializable {
    @Inject
    private PersistenceManager persistenceManager;
    private EntityManager entityManager;
    public static List<Point> points = new ArrayList<>();
    public static Logger logger = Logger.getLogger(DBManagerBean.class.getName());

    @PostConstruct
    public void init() {
        entityManager = persistenceManager.getEmf().createEntityManager();
        loadFromDB();
    }

    public void loadFromDB(){
        try {
            entityManager.getTransaction().begin();
            points = new ArrayList<>(entityManager.createQuery("SELECT p FROM Point p", Point.class).getResultList());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void addPoint(Point point) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(point);
        entityTransaction.commit();

        points.add(0, point);
    }


    public List<Point> getPoints(){
        return points;
    }
    public void clear(){
        points.clear();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.createQuery("DELETE FROM Point").executeUpdate();
            entityTransaction.commit();

            points.clear();
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
    }
}
