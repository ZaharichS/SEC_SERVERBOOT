package reg.example.SecServer.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reg.example.SecServer.entity.AuthorEntity;
import reg.example.SecServer.repo.AuthorRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class AuthorService {
    private final AuthorRepo repo;

    public List<AuthorEntity> getAll() {
        return repo.findAll();
    }

    public Optional<AuthorEntity> findById(Long id) {
        return repo.findById(id);
    }

    public AuthorEntity save(@Valid AuthorEntity data) {
        return repo.save(data);
    }

    // Обновление автора
    public void update(AuthorEntity data) {
        repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
