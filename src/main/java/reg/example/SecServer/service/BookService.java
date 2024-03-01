package reg.example.SecServer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reg.example.SecServer.entity.BookEntity;
import reg.example.SecServer.repo.BookRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepo repo;

    public List<BookEntity> getAll() {
        return repo.findAll();
    }

    public Optional<BookEntity> findById(Long id) {
        return repo.findById(id);
    }

    public BookEntity save(BookEntity data) {
        return repo.save(data);
    }

    // Обновление автора
    public void update(BookEntity data) {
        repo.save(data);
    }
}
