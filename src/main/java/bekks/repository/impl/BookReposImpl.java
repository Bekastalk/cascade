package bekks.repository.impl;

import bekks.config.Config;
import bekks.entity.Author;
import bekks.entity.Book;
import bekks.entity.Publisher;
import bekks.repository.BookRepos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;

public class BookReposImpl implements BookRepos {
    private final EntityManagerFactory entityManagerFactory = Config.getEntityManager();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveBook(Long aid,Book book) {
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, aid);
        book.setAuthor(author);
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateBookAuthor(Long bID, Long aID) {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("""
                        update books set author_id=:aId where id=:id
                        """).setParameter("aId", aID)
                .setParameter("id", bID).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Book getBookAndPublisherByBookId(Long bID) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("""
                select a, b from Book b
                join b.publishers a
                where b.id = :id
                """, Book.class).setParameter("id", bID);


        entityManager.getTransaction().commit();
        entityManager.close();
        return null;
    }

    @Override
    public void deleteBookByAuthorId(Long authorId) {
        entityManager.getTransaction().begin();
        entityManager.find(Author.class, authorId);
        Query deleteQuery = entityManager.createQuery(
                "delete from Book b where b.author.id = :authorId");
        deleteQuery.setParameter("authorId", authorId);
        int deletedCount = deleteQuery.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

        if (deletedCount == 0) {
            throw new RuntimeException("No books found for the author with id: " + authorId);
        }
    }



}
