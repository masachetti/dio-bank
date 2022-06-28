import bank.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Bank-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.getTransaction().begin();
        Client cliente = new Client("Carlos");
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();


        entityManager.close();
        entityManagerFactory.close();
    }
}
