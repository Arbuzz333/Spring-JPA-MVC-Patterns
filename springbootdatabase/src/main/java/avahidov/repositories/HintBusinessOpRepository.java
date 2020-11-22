package avahidov.repositories;

import avahidov.entities.HintBusinessOpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HintBusinessOpRepository extends JpaRepository<HintBusinessOpEntity, Long> {

}
