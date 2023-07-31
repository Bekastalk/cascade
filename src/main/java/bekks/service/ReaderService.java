package bekks.service;

import bekks.entity.Reader;

import java.util.List;

public interface ReaderService {
    void saveReader(Reader reader);

    void updateReader(Long id, String name, int age, String email);

    Reader getReaderByBookId(Long bookId);

    void deleteReaderById(Long id);

    List<Reader> getReadersByAuthorId(Long aId);
}
