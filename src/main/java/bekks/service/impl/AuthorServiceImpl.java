package bekks.service.impl;

import bekks.entity.Author;
import bekks.repository.AuthorRepos;
import bekks.repository.impl.AuthorReposImpl;
import bekks.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    AuthorRepos authorRepos=new AuthorReposImpl();
    @Override
    public void saveAuthor(Author author) {
        authorRepos.saveAuthor(author);
    }

    @Override
    public void updateAuthor(Long id, Author author) {
        authorRepos.updateAuthor(id, author);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepos.getAuthorById(id);
    }

    @Override
    public List<Author> getAuthorsByPublisherId(Long pId) {
        return authorRepos.getAuthorsByPublisherId(pId);
    }

    @Override
    public void deleteAuthorById(Long l) {
        authorRepos.deleteAuthorById(l);
    }

    @Override
    public void assignAuthorToPublisher(Long aId, Long pId) {
        authorRepos.assignAuthorToPublisher(aId,pId);
    }
}
