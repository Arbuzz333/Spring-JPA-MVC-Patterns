package avahidov.mappers;

import avahidov.entities.HintBusinessOpEntity;
import avahidov.entities.HintBusinessStepEntity;
import avahidov.entities.HintChannelEntity;
import avahidov.entities.HintHintEntity;
import avahidov.entities.HintUserEntity;
import avahidov.uservo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class EntityMapperImpl implements EntityMapper {

    @Autowired
    private HintChannelEntityChanelMapper mapper;

    @Override
    public HintUserEntity userToEntity(User user) {
        HintUserEntity userEntity = mapper.hintUserEntityToUserItem(user);

        HintBusinessOpEntity businessOpEntity = userEntity.getRefHintBusinessOpEntity();
        businessOpEntity.setRefHintUserEntities(userEntity);

        List<HintBusinessStepEntity> refStepEntities = businessOpEntity.getRefHintBusinessStepEntities();
        refStepEntities.replaceAll(businessStepEntity -> {
            businessStepEntity.setRefHintBusinessOpEntity(businessOpEntity);
            return businessStepEntity;
        });

        List<HintChannelEntity> refChannelEntity = businessOpEntity.getRefHintChannelEntity();
        refChannelEntity.forEach(channelEntity -> {
            channelEntity.setRefHintBusinessOpEntities(businessOpEntity);

            List<HintHintEntity> refHintEntities = channelEntity.getRefHintHintEntities();
            refHintEntities.forEach(hintEntity -> hintEntity.setrefHintChannelEntity(channelEntity));
        });
        return userEntity;
    }
}
