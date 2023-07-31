package bekks.service;

import bekks.entity.Author;

import java.util.List;

public interface AuthorService {
    void saveAuthor(Author author);

    void updateAuthor(Long id, Author author);

    Author getAuthorById(Long id);

    List<Author> getAuthorsByPublisherId(Long pId);

    void deleteAuthorById(Long l);

    void assignAuthorToPublisher(Long aId, Long pId);


}
