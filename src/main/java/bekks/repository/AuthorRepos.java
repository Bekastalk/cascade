package bekks.repository;

import bekks.entity.Author;

import java.util.List;

public interface AuthorRepos {
    void saveAuthor(Author author);

    void updateAuthor(Long id, Author author);

    Author getAuthorById(Long id);


    List<Author> getAuthorsByPublisherId(Long pId);

    void deleteAuthorById(Long id);

    void assignAuthorToPublisher(Long aId, Long pId);
}
