package bekks.repository.impl;

import bekks.config.Config;
import bekks.entity.Author;
import bekks.entity.Publisher;
import bekks.repository.AuthorRepos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class AuthorReposImpl implements AuthorRepos {
    private final EntityManagerFactory entityManagerFactory = Config.getEntityManager();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveAuthor(Author author) {
        entityManager.getTransaction().begin();
        entityManager.persist(author);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateAuthor(Long id, Author author) {
        entityManager.getTransaction().begin();
        Author author1 = entityManager.find(Author.class, id);
        if (author1 != null) {
            author1.setFirstName(author.getFirstName());
            author1.setLastName(author.getLastName());
            author1.setEmail(author.getEmail());
            author1.setDateOfBirth(author.getDateOfBirth());
            author1.setCountry(author.getCountry());
            author1.setGender(author.getGender());
            System.out.println("Success updated");
            entityManager.getTransaction().commit();
        } else {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("President with id " + id + " not found.");
        }

        entityManager.close();
    }

    @Override
    public Author getAuthorById(Long id) {
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return author;
    }

    @Override
    public List<Author> getAuthorsByPublisherId(Long pId) {
        entityManager.getTransaction().begin();
        Publisher publisher = entityManager.find(Publisher.class, pId);

        Query query = entityManager.createQuery("""
            select a from Publisher p
            join p.authors a
            where p.id = :id
            """, Author.class).setParameter("id", pId);

        List<Author> authors = query.getResultList();

        entityManager.getTransaction().commit();

        return authors;
    }

    @Override
    public void deleteAuthorById(Long id) {
        entityManager.getTransaction().begin();

        // Находим автора по идентификатору
        Author author = entityManager.find(Author.class, id);
        if (author != null) {
            // Удаляем связанные записи в таблице "publishers_authors"
            Query deleteQuery = entityManager.createNativeQuery("""
                delete from publishers_authors pa
                where pa.author = :author
                """).setParameter("author", author);
            deleteQuery.executeUpdate();

            // Удаляем самого автора
            entityManager.remove(author);

            entityManager.getTransaction().commit();
            entityManager.close();
        } else {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Author with id " + id + " not found.");
        }
    }

    @Override
    public void assignAuthorToPublisher(Long authorId, Long publisherId) {
        entityManager.getTransaction().begin();
        List<Author>authors = new ArrayList<>();
        List<Publisher>publishers = new ArrayList<>();
        Author author = entityManager.find(Author.class, authorId);
        Publisher publisher = entityManager.find(Publisher.class, publisherId);
        authors.add(author);
        publishers.add(publisher);

        publisher.setAuthors(authors);
        author.setPublishers(publishers);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
