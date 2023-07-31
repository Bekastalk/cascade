package bekks.repository.impl;

import bekks.config.Config;
import bekks.entity.Publisher;
import bekks.repository.PublisherRepos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class PublisherReposImpl implements PublisherRepos {
    private final EntityManagerFactory entityManagerFactory = Config.getEntityManager();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void savePublisher(Publisher publisher) {
        entityManager.getTransaction().begin();

        entityManager.persist(publisher);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Publisher getPublisherById(Long id) {
        entityManager.getTransaction().begin();

        Publisher publisher = entityManager.find(Publisher.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();
        return publisher;
    }

    @Override
    public List<Publisher> getAllPublishers() {
        entityManager.getTransaction().begin();

        List<Publisher> resultList = entityManager.createQuery("""
                select p from Publisher p order by p.name asc
                """, Publisher.class).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public String updatePublisher(Long id, Publisher publisher) {
        entityManager.getTransaction().begin();

        Publisher publisher1 = entityManager.find(Publisher.class, id);
        if(publisher1!=null){
            publisher1.setName(publisher.getName());
            publisher1.setAddress(publisher.getAddress());
            entityManager.getTransaction().commit();
        } else {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("President with id " + id + " not found.");
        }
        entityManager.close();
        return "Success update";
    }

    @Override
    public void deletePublisherByName(String name) {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("""
                delete from publishers where name=:nameP
                """,Publisher.class)
                        .setParameter("nameP", name)
                                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
