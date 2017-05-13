package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valeria on 02.05.2017.
 */
public class CoffeeOrderManager {
    private List<CoffeeOrder> coffeeOrderList;
    private CoffeeOrder coffeeOrder;

    public void createNewOrder(CoffeeOrder coffeeOrder) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoffeeOrder");
        EntityManager em =  emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(coffeeOrder);
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<CoffeeOrder> getCoffeeOrderList() {
        //TODO create one emf on class
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoffeeOrder");
        EntityManager em =  emf.createEntityManager();

        this.coffeeOrderList = new ArrayList<>();

        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("get all");
            this.coffeeOrderList = q.getResultList();
        } finally {
            em.close();
            emf.close();
        }
        return coffeeOrderList;
    }

    public CoffeeOrder getCoffeeOrder() {
        return coffeeOrder;
    }

    public void foundById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoffeeOrder");
        EntityManager em =  emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("get by id");
            q.setParameter("id", id);
            this.coffeeOrder = (CoffeeOrder) q.getSingleResult();
        } finally {
            em.close();
            emf.close();
        }
    }

    public void setCoffeeOrder(CoffeeOrder coffeeOrder) {
        this.coffeeOrder = coffeeOrder;
    }

    public void setCoffeeOrderList(List<CoffeeOrder> coffeeOrderList) {
        this.coffeeOrderList = coffeeOrderList;
    }
}
