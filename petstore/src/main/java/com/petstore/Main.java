package com.petstore;

import com.petstore.models.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstorePU");
        EntityManager em = emf.createEntityManager();

        try {
            // Démarrer une transaction
            em.getTransaction().begin();

            // Créer des instances de Address
            Address address1 = new Address();
            Address address2 = new Address("456", "Second St", "67890", "Othertown");
            Address address3 = new Address("789", "Third St", "24680", "Sometown");

            // Persister les instances de Address
            em.persist(address1);
            em.persist(address2);
            em.persist(address3);

            // Créer des instances de PetStore
            petstore store1 = new petstore("PetStore A", "Manager A", address1);
            petstore store2 = new petstore("PetStore B", "Manager B", address2);
            petstore store3 = new petstore("PetStore C", "Manager C", address3);

            
            // Persister les instances de PetStore
            em.persist(store1);
            em.persist(store2);
            em.persist(store3);

            // Créer des instances d'animaux
            Fish fish1 = new Fish(new Date(), "Gold", store1, FishLivEnv.FRESH_WATER);
            Fish fish2 = new Fish(new Date(), "Blue", store1, FishLivEnv.SEA_WATER);
            Cat cat1 = new Cat(new Date(), "White", store2, "Chip123");

            // Persister les instances d'animaux
            em.persist(fish1);
            em.persist(fish2);
            em.persist(cat1);

            // Commit de la transaction
            em.getTransaction().commit();

            // Requête pour obtenir tous les animaux d'une animalerie spécifique
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.petStore.id = :store_id", Animal.class);
            query.setParameter("store_id", store1.getId());
            List<Animal> animalsFromStore1 = query.getResultList();

            for (Animal animal : animalsFromStore1) {
                System.out.println(animal.getClass().getSimpleName() + " - " + ((Object) animal).getColor());
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}
