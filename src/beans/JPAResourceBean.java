package beans;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This is an Application Scoped bean that holds the JPA
 * EntityManagerFactory.  By making this bean Applciation scoped the
 * EntityManagerFactory resource will be created only once for the application
 * and cached here.
 *
 * Created by Valeria on 01.05.2017.
 */
public class JPAResourceBean {
    protected EntityManagerFactory emf;

    public EntityManagerFactory getEMF (){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("CoffeeUnit");
        }
        return emf;
    }
}
