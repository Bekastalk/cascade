package bekks.config;

import bekks.entity.Author;
import bekks.entity.Book;
import bekks.entity.Publisher;
import bekks.entity.Reader;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class Config {
    public static EntityManagerFactory getEntityManager() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "12345");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL, "true");
        Configuration configuration = new Configuration();
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Reader.class);
        configuration.addAnnotatedClass(Publisher.class);
        configuration.addAnnotatedClass(Author.class);
        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
    }
}
