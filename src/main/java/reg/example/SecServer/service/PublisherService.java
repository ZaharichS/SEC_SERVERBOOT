package reg.example.SecServer.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reg.example.SecServer.entity.PublisherEntity;
import reg.example.SecServer.repo.PublisherRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class PublisherService {
    private final PublisherRepo repo;

    public List<PublisherEntity> getAll() {
        return repo.findAll();
    }

    public Optional<PublisherEntity> findById(Long id) {
        return repo.findById(id);
    }

    public PublisherEntity save(@Valid PublisherEntity data) {
        return repo.save(data);
    }

    // Обновление автора
    public void update(PublisherEntity data) {
        repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
