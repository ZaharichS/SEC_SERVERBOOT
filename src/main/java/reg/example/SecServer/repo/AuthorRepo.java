package reg.example.SecServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import reg.example.SecServer.entity.AuthorEntity;

public interface AuthorRepo extends JpaRepository<AuthorEntity, Long> {}
