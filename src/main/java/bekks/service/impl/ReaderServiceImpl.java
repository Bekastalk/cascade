package bekks.service.impl;

import bekks.entity.Reader;
import bekks.repository.ReaderRepos;
import bekks.repository.impl.ReaderReposImpl;
import bekks.service.ReaderService;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    ReaderRepos readerRepos =new ReaderReposImpl();
    @Override
    public void saveReader(Reader reader) {
        readerRepos.saveReader(reader);
    }

    @Override
    public void updateReader(Long id, String name, int age, String email) {
        readerRepos.updateReader(id, name, age, email);
    }

    @Override
    public Reader getReaderByBookId(Long bookId) {
        return readerRepos.getReaderByBookId(bookId);
    }

    @Override
    public void deleteReaderById(Long id) {
        readerRepos.deleteReaderById(id);
    }

    @Override
    public List<Reader> getReadersByAuthorId(Long aId) {
        return readerRepos.getReadersByAuthorId(aId);
    }
}
