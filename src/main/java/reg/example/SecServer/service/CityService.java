package reg.example.SecServer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reg.example.SecServer.entity.CityEntity;
import reg.example.SecServer.repo.CityRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepo repo;

    public List<CityEntity> getAll() {
        return repo.findAll();
    }

    public Optional<CityEntity> findById(Long id) {
        return repo.findById(id);
    }

    public CityEntity save(CityEntity data) {
        return repo.save(data);
    }

    // Обновление автора
    public void update(CityEntity data) {
        repo.save(data);
    }
}
