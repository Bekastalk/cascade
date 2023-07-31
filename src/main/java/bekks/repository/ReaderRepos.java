package bekks.repository;

import bekks.entity.Reader;

import java.util.List;

public interface ReaderRepos {
    void saveReader(Reader reader);

    void updateReader(Long id, String name, int age, String email);

    Reader getReaderByBookId(Long bookId);

    void deleteReaderById(Long id);

    List<Reader> getReadersByAuthorId(Long aId);
}
