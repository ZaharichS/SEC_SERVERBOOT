package reg.example.SecServer.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reg.example.SecServer.entity.BookEntity;
import reg.example.SecServer.repo.BookRepo;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class BookService {
    private final BookRepo repo;

    public List<BookEntity> getAll() {
        return repo.findAll();
    }

    public Optional<BookEntity> findById(Long id) {
        return repo.findById(id);
    }

    public BookEntity save(@Valid BookEntity data) {
        return repo.save(data);
    }

    // Обновление автора
    public void update(BookEntity data) {
        repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Поиск по двум пар-ам
/*    public Iterable<BookEntity> findByPublisher(String title, String city) {
        return repo.findDistinctByPublishing_PublisherOrPublishing_City(title, city);
    }*/

    // Поиск по id автора
    public List<BookEntity> findByAuthorId (Long id) {
        return repo.findBookEntityByAuthor_Id(id);
    }

    // Поиск по фамилии автора
    public List<BookEntity> findByAuthorSurname (String surname) {
        return repo.findBookEntityByAuthor_Author_surname(surname);
    }

    // Поиск по названию книги
    public List<BookEntity> findByTitle(String title) {
        return repo.findBookEntityByTitle(title);
    }
}
