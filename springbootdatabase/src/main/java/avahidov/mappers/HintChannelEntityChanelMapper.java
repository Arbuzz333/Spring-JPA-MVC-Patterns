package avahidov.mappers;


import avahidov.dao.HintBusinessOpEntity;
import avahidov.dao.HintBusinessStepEntity;
import avahidov.dao.HintChannelEntity;
import avahidov.dao.HintHintEntity;
import avahidov.dao.HintUserEntity;
import avahidov.hintvo.BusinessOpItem;
import avahidov.hintvo.Channel;
import avahidov.hintvo.Hint;
import avahidov.hintvo.StepItem;
import avahidov.hintvo.UserItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;



@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface HintChannelEntityChanelMapper {

    @Mappings({
            @Mapping(target="modifiedDate", source="channel.modDate", dateFormat = "dd-MM-yyyy"),
            @Mapping(target="refHintBusinessOpEntities", source="channel.businessOp"),
            @Mapping(target="refHintHintEntities", source="channel.hint")
    })
    HintChannelEntity channelToHintChannelEntity(Channel channel);

    @Mappings({
            @Mapping(target="modDate", source="hintChannelEntity.modifiedDate"),
            @Mapping(target="businessOp", source="hintChannelEntity.refHintBusinessOpEntities"),
            @Mapping(target="hint", source="hintChannelEntity.refHintHintEntities")
    })
    Channel hintChannelEntityToChannel(HintChannelEntity hintChannelEntity);

    @Mappings({
            @Mapping(target = "hintText", source = "hint.text"),
            @Mapping(target = "hintType", source = "hint.type"),
            @Mapping(target = "modifiedDate", source = "hint.modDate", dateFormat = "dd-MM-yyyy")
    })
    HintHintEntity hintToHintHintEntity(Hint hint);

    @Mappings({
            @Mapping(target = "text", source = "hintHintEntity.hintText"),
            @Mapping(target = "type", source = "hintHintEntity.hintType"),
            @Mapping(target = "modDate", source = "hintHintEntity.modifiedDate", dateFormat = "dd-MM-yyyy")
    })
    Hint hintHintEntityToHint(HintHintEntity hintHintEntity);

    @Mappings({
            @Mapping(target = "modifiedDate", source = "businessOpItem.modDate", dateFormat = "dd-MM-yyyy"),
            @Mapping(target = "refHintBusinessStepEntities", source = "businessOpItem.step"),
            @Mapping(target = "refHintUserEntities", source = "businessOpItem.user")
    })
    HintBusinessOpEntity businessOpItemToHintBusinessOpEntity(BusinessOpItem businessOpItem);

    List<HintBusinessOpEntity> businessOpItemToHintBusinessOpEntity(List<BusinessOpItem> businessOpItem);

    @Mappings({
            @Mapping(target = "modDate", source = "hintBusinessOpEntity.modifiedDate", dateFormat = "dd-MM-yyyy"),
            @Mapping(target = "step", source = "hintBusinessOpEntity.refHintBusinessStepEntities"),
            @Mapping(target = "user", source = "hintBusinessOpEntity.refHintUserEntities")
    })
    BusinessOpItem hintBusinessOpEntityToBusinessOpItem(HintBusinessOpEntity hintBusinessOpEntity);

    List<BusinessOpItem> hintBusinessOpEntityToBusinessOpItem(List<HintBusinessOpEntity> hintBusinessOpEntity);

    @Mappings({
            @Mapping(target = "modifiedDate", source = "stepItem.modDate", dateFormat = "dd-MM-yyyy"),
    })
    HintBusinessStepEntity hintBusinessStepEntityToStepItem(StepItem stepItem);

    List<HintBusinessStepEntity> hintBusinessStepEntityToStepItem(List<StepItem> stepItem);

    @Mappings({
            @Mapping(target = "modDate", source = "hintBusinessStepEntity.modifiedDate", dateFormat = "dd-MM-yyyy"),
    })
    StepItem stepItemToHintBusinessStepEntity(HintBusinessStepEntity hintBusinessStepEntity);

    List<StepItem> stepItemToHintBusinessStepEntity(List<HintBusinessStepEntity> hintBusinessStepEntity);

    @Mappings({
            @Mapping(target = "createDate", source = "userItem.createDate", dateFormat = "dd-MM-yyyy"),
            @Mapping(target = "modifiedDate", source = "userItem.modDate", dateFormat = "dd-MM-yyyy")
    })
    HintUserEntity hintUserEntityToUserItem(UserItem userItem);

    @Mappings({
            @Mapping(target = "createDate", source = "hintUserEntity.createDate", dateFormat = "dd-MM-yyyy"),
            @Mapping(target = "modDate", source = "hintUserEntity.modifiedDate", dateFormat = "dd-MM-yyyy")
    })
    UserItem userItemToHintUserEntity(HintUserEntity hintUserEntity);

}
