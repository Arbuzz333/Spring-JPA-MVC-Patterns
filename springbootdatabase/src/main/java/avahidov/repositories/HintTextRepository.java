package avahidov.repositories;


import avahidov.entities.HintTextEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface HintTextRepository extends JpaRepository<HintTextEntity, Long> {
}
