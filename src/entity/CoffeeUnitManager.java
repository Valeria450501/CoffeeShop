package entity;

import beans.JPAResourceBean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valeria on 01.05.2017.
 */
public class CoffeeUnitManager {
    protected JPAResourceBean jpaResourceBean;
    private List<CoffeeUnit> coffeeUnitList;
    private CoffeeUnit singleCoffeeUnit;

    public CoffeeUnitManager() {
    }

    public List<CoffeeUnit> getCoffeeUnitList() {
        //TODO create one emf on class
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoffeeUnit");
        EntityManager em =  emf.createEntityManager();

        this.coffeeUnitList = new ArrayList<>();

        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("get all");
            this.coffeeUnitList = q.getResultList();
        } finally {
            em.close();
            emf.close();
        }

        return coffeeUnitList;
    }

    public int getPrice(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoffeeUnit");
        EntityManager em =  emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("get by id");
            q.setParameter("id", id);
            this.singleCoffeeUnit = (CoffeeUnit) q.getSingleResult();
            return this.singleCoffeeUnit.getPrice();
        } finally {
            em.close();
            emf.close();
        }
    }

    public String getGrade(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoffeeUnit");
        EntityManager em =  emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("get by id");
            q.setParameter("id", id);
            this.singleCoffeeUnit = (CoffeeUnit) q.getSingleResult();
            return this.singleCoffeeUnit.getGrade();
        } finally {
            em.close();
            emf.close();
        }
    }

    public void setCoffeeUnitList(List<CoffeeUnit> coffeeUnitList) {
        this.coffeeUnitList = coffeeUnitList;
    }

    public JPAResourceBean getJpaResourceBean() {
        return jpaResourceBean;
    }

    public void setJpaResourceBean(JPAResourceBean jpaResourceBean) {
        this.jpaResourceBean = jpaResourceBean;
    }
}
