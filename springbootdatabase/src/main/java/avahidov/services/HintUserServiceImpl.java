package avahidov.services;

import avahidov.entities.HintUserEntity;
import avahidov.mappers.EntityMapper;
import avahidov.repositories.HintUserRepository;
import avahidov.uservo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class HintUserServiceImpl implements HintUserService {

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private HintUserRepository userRepository;

    @Override
    @Transactional
    public HintUserEntity saveToDataBase(HintUserEntity userEntity) {
        HintUserEntity saved = userRepository.save(userEntity);
        return saved;
    }

    @Override
    @Transactional
    public HintUserEntity saveToDataBase(User user) {
        HintUserEntity userEntity = entityMapper.userToEntity(user);
        HintUserEntity saved = userRepository.save(userEntity);
        return saved;
    }

    @Override
    @Transactional
    public void deleteFromDataBase(HintUserEntity userEntity) {
        userRepository.delete(userEntity);
    }
}
