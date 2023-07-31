package bekks.service.impl;

import bekks.entity.Book;
import bekks.repository.BookRepos;
import bekks.repository.impl.BookReposImpl;
import bekks.service.BookService;

public class BookServiceImpl implements BookService {
    BookRepos bookRepos=new BookReposImpl();
    @Override
    public void saveBook(Long aid,Book book) {
        bookRepos.saveBook(aid,book);
    }

    @Override
    public void updateBookAuthor(Long bID,Long aID) {
        bookRepos.updateBookAuthor(bID,aID);
    }

    @Override
    public Book getBookAndPublisherByBookId(Long bID) {
        return bookRepos.getBookAndPublisherByBookId(bID);
    }

    @Override
    public void deleteBookByAuthorId(Long aid) {
        bookRepos.deleteBookByAuthorId(aid);
    }
}
