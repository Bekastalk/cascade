package bekks.repository.impl;

import bekks.config.Config;
import bekks.entity.Author;
import bekks.entity.Reader;
import bekks.repository.ReaderRepos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class ReaderReposImpl implements ReaderRepos {
    private final EntityManagerFactory entityManagerFactory = Config.getEntityManager();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveReader(Reader reader) {
        entityManager.getTransaction().begin();
        entityManager.persist(reader);
        System.out.println(reader.getName() + " Success saved! Ты красавчик, ты гений. Ты мой зозяин!!!");
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateReader(Long id, String name, int age, String email) {
        entityManager.getTransaction().begin();

        entityManager.createQuery("""
                        update Reader set name=:name, age=:age, email=:email where id=:id
                        """).setParameter("name", name)
                .setParameter("age", age)
                .setParameter("email", email)
                .setParameter("id", id)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Reader getReaderByBookId(Long bookId) {
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, bookId);
        Reader books = entityManager.createQuery("""
                        select r from Book b join b.reader r
                        where b.id=:id
                        """, Reader.class).setParameter("id", bookId)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return books;
    }

    @Override
    public void deleteReaderById(Long id) {
        entityManager.getTransaction().begin();

        // Находим читателя по идентификатору
        Reader reader = entityManager.find(Reader.class, id);
        if (reader != null) {
            // Устанавливаем null для всех записей в таблице книг, где читатель равен удаляемому читателю
            entityManager.createQuery("""
                    update Book b set b.reader = null where b.reader = :reader
                    """).setParameter("reader", reader).executeUpdate();

            // Удаляем самого читателя
            entityManager.remove(reader);

            entityManager.getTransaction().commit();
            entityManager.close();
        } else {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Reader with id " + id + " not found.");
        }
    }

    @Override
    public List<Reader> getReadersByAuthorId(Long aId) {
        entityManager.getTransaction().begin();

        List<Reader> readers = entityManager.createQuery("""
            select distinct r from Book b 
            join b.author a
            join b.reader r
            where a.id = :aId
            """, Reader.class)
                .setParameter("aId", aId)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return readers;
    }
}
