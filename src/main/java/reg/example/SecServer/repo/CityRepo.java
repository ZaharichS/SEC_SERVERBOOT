package reg.example.SecServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import reg.example.SecServer.entity.AuthorEntity;
import reg.example.SecServer.entity.CityEntity;

public interface CityRepo extends JpaRepository<CityEntity, Long> {}
