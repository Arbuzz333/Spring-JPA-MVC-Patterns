package avahidov.mappers;

import avahidov.entities.HintBusinessOpEntity;
import avahidov.entities.HintBusinessStepEntity;
import avahidov.entities.HintChannelEntity;
import avahidov.entities.HintHintEntity;
import avahidov.entities.HintUserEntity;
import avahidov.uservo.BusinessOp;
import avahidov.uservo.ChannelItem;
import avahidov.uservo.HintItem;
import avahidov.uservo.StepItem;
import avahidov.uservo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EntityMapperImpl implements EntityMapper {

    @Autowired
    private HintEntityItemMapper mapper;

    @Autowired
    DateMapperImpl dateMapper;

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

    @Override
    public User entityToUserLite(HintUserEntity entity) {
        User user = new User();
        user.setUser(entity.getUser());
        user.setCreateDate(dateMapper.asString(entity.getCreateDate()));
        user.setModDate(dateMapper.asString(entity.getModifiedDate()));

        BusinessOp businessOp = new BusinessOp();
        businessOp.setCode(entity.getRefHintBusinessOpEntity().getCode());

        List<ChannelItem> channelItems = entity.getRefHintBusinessOpEntity().getRefHintChannelEntity()
                .stream().map(hintChannelEntity -> {
                    ChannelItem item = new ChannelItem();
                    item.setCode(hintChannelEntity.getCode());

                    List<HintItem> hintItemList = hintChannelEntity.getRefHintHintEntities().stream().map(hint -> {
                        HintItem hintItem = new HintItem();
                        hintItem.setCode(hint.getCode());
                        return hintItem;
                    }).collect(Collectors.toList());
                    item.setHint(hintItemList);

                    return item;
                }).collect(Collectors.toList());
        businessOp.setChannel(channelItems);

        List<HintBusinessStepEntity> hintBusinessStepList =
                entity.getRefHintBusinessOpEntity().getRefHintBusinessStepEntities();

        List<StepItem> stepItemList = hintBusinessStepList.stream().map(step -> {
            StepItem item = new StepItem();
            item.setCode(step.getCode());
            return item;
        }).collect(Collectors.toList());
        businessOp.setStep(stepItemList);

        user.setBusinessOp(businessOp);

        return user;
    }
}
