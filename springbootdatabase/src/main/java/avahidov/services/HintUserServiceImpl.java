package avahidov.services;

import avahidov.entities.HintUserEntity;
import avahidov.repositories.HintUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class HintUserServiceImpl implements HintUserService {

    @Autowired
    private HintUserRepository userRepository;

    @Override
    @Transactional
    public HintUserEntity saveToDataBase(HintUserEntity userEntity) {
        HintUserEntity saved = userRepository.save(userEntity);
        return saved;
    }
}
