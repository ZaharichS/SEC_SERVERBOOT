package reg.example.SecServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import reg.example.SecServer.entity.BookEntity;

public interface BookRepo extends JpaRepository<BookEntity, Long> {
    Iterable<BookEntity> findDistinctByPublishing_PublisherOrPublisher_City(String title, String city);
}
