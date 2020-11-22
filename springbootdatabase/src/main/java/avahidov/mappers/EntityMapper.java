package avahidov.mappers;

import avahidov.entities.HintUserEntity;
import avahidov.uservo.User;



public interface EntityMapper {

    HintUserEntity userToEntity(User user);
}
