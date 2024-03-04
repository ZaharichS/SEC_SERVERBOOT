package reg.example.SecServer.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reg.example.SecServer.entity.GenreEntity;
import reg.example.SecServer.repo.GenreRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class GenreService {
    private final GenreRepo repo;

    public List<GenreEntity> getAll() {
        return repo.findAll();
    }

    public Optional<GenreEntity> findById(Long id) {
        return repo.findById(id);
    }

    public GenreEntity save(@Valid GenreEntity data) {
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
