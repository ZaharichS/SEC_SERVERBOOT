package reg.example.SecServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import reg.example.SecServer.entity.BookEntity;

import java.util.List;

public interface BookRepo extends JpaRepository<BookEntity, Long> {
    //Iterable<BookEntity> findDistinctByPublishing_PublisherOrPublishing_City(String title, String city);
    List<BookEntity> findBookEntityByAuthor_Id(Long id);
    List<BookEntity> findBookEntityByAuthor_Author_surname(String name);
    List<BookEntity> findBookEntityByTitle(String title);
}
