package bekks.repository;

import bekks.entity.Publisher;

import java.util.List;

public interface PublisherRepos {

    void savePublisher(Publisher publisher);

    Publisher getPublisherById(Long id);

    List<Publisher> getAllPublishers();

    String updatePublisher(Long id, Publisher publisher);

    void deletePublisherByName(String name);
}

/*3.1) Publisher кошуу учун SavePublisher()
деген метод тузуп, сакталган Publisher ди кайтаруу керек.

3.2) Издательстводо getPublisherById(),
getAllPublishers()(аты боюнча сорттоп чыгаруу),
updatePublisher(),
deletePublisherByName() (издательствону очургондо,
 ага тиешелуу китептер жана авторлор  очпошу керек),
 методдорун тузуп, ишке ашыруу.*/