package reg.example.SecServer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reg.example.SecServer.entity.GenreEntity;
import reg.example.SecServer.repo.GenreRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepo repo;

    public List<GenreEntity> getAll() {
        return repo.findAll();
    }

    public Optional<GenreEntity> findById(Long id) {
        return repo.findById(id);
    }

    public GenreEntity save(GenreEntity data) {
        return repo.save(data);
    }

    // Обновление автора
    public void update(GenreEntity data) {
        repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
