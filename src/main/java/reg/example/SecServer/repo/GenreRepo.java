package reg.example.SecServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import reg.example.SecServer.entity.AuthorEntity;
import reg.example.SecServer.entity.GenreEntity;

public interface GenreRepo extends JpaRepository<GenreEntity, Long> {}