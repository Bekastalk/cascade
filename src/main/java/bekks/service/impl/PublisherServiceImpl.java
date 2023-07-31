package bekks.service.impl;

import bekks.entity.Publisher;
import bekks.repository.PublisherRepos;
import bekks.repository.impl.PublisherReposImpl;
import bekks.service.PublisherService;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {
    PublisherRepos publisherRepos=new PublisherReposImpl();
    @Override
    public void savePublisher(Publisher publisher) {
        publisherRepos.savePublisher(publisher);
    }

    @Override
    public Publisher getPublisherById(Long id) {
        return publisherRepos.getPublisherById(id);
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepos.getAllPublishers();
    }

    @Override
    public String updatePublisher(Long id, Publisher publisher) {
        return publisherRepos.updatePublisher(id, publisher);
    }

    @Override
    public void deletePublisherByName(String name) {
        publisherRepos.deletePublisherByName(name);
    }
}
