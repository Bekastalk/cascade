package bekks.service;

import bekks.entity.Book;

public interface BookService {
    void saveBook(Long aid,Book book);

    void updateBookAuthor(Long bID,Long aID);

    Book getBookAndPublisherByBookId(Long bID);

    void deleteBookByAuthorId(Long aid);
}
