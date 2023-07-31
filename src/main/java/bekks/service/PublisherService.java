package bekks.service;

import bekks.entity.Publisher;

import java.util.List;

public interface PublisherService {
    void savePublisher(Publisher publisher);

    Publisher getPublisherById(Long id);

    List<Publisher> getAllPublishers();

    String updatePublisher(Long id, Publisher publisher);

    void deletePublisherByName(String name);
}
