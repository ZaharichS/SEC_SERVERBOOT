package reg.example.SecServer.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reg.example.SecServer.entity.BookEntity;
import reg.example.SecServer.repo.BookRepo;

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
}
