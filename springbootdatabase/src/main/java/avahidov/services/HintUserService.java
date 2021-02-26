package avahidov.services;


import avahidov.entities.HintUserEntity;
import avahidov.uservo.User;

import java.util.List;


public interface HintUserService {

    HintUserEntity saveToDataBase(HintUserEntity userEntity);

    HintUserEntity saveToDataBase(User user);

    List<User> createUserList(List<User> userList);

    void deleteFromDataBase(HintUserEntity userEntity);

}
