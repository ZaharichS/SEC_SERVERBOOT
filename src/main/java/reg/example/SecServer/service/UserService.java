package reg.example.SecServer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reg.example.SecServer.entity.UserEntity;
import reg.example.SecServer.repo.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo repo;

    public List<UserEntity> getAll() {
        return repo.findAll();
    }

    public Optional<UserEntity> findById(Long id) {
        return repo.findById(id);
    }

    public UserEntity save(UserEntity data) {
        return repo.save(data);
    }

    // Обновление автора
    public void update(UserEntity data) {
        repo.save(data);
    }
}
