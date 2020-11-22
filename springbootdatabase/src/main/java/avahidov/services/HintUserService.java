package avahidov.services;


import avahidov.entities.HintUserEntity;
import avahidov.uservo.User;



public interface HintUserService {

    HintUserEntity saveToDataBase(HintUserEntity userEntity);

    HintUserEntity saveToDataBase(User user);

    void deleteFromDataBase(HintUserEntity userEntity);

}
