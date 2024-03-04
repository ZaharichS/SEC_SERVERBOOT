package reg.example.SecServer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reg.example.SecServer.entity.AuthorEntity;
import reg.example.SecServer.repo.AuthorRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepo repo;

    public List<AuthorEntity> getAll() {
        return repo.findAll();
    }

    public Optional<AuthorEntity> findById(Long id) {
        return repo.findById(id);
    }

    public AuthorEntity save(AuthorEntity data) {
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
