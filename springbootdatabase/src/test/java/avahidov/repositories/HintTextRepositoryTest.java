package avahidov.repositories;

import avahidov.entities.HintTextEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class HintTextRepositoryTest {

    @Autowired
    HintTextRepository textRepository;

    @Test
    void hintTextRepositoryTest() {

        List<HintTextEntity> hintTextEntities = textRepository.findAll();
        for (HintTextEntity textEntity : hintTextEntities) {
            assertNotNull(textEntity.getId());
        }
    }

}