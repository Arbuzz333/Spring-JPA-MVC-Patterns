package avahidov.repositories;

import avahidov.entities.HintUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HintUserRepository extends JpaRepository<HintUserEntity, Long> {
}
