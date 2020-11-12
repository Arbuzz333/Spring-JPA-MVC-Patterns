package avahidov.mappers;


import avahidov.dao.HintBusinessOpEntity;
import avahidov.dao.HintBusinessStepEntity;
import avahidov.dao.HintChannelEntity;
import avahidov.dao.HintHintEntity;
import avahidov.dao.HintUserEntity;
import avahidov.uservo.BusinessOp;
import avahidov.uservo.ChannelItem;
import avahidov.uservo.StepItem;
import avahidov.uservo.HintItem;
import avahidov.uservo.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;



@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface HintChannelEntityChanelMapper {

    @Mappings({
            @Mapping(target="modifiedDate", source="channel.modDate"),
            @Mapping(target="refHintHintEntities", source="channel.hint"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "refHintBusinessOpEntities", ignore = true)
    })
    HintChannelEntity channelToHintChannelEntity(ChannelItem channel);

    @Mappings({
            @Mapping(target="modDate", source="hintChannelEntity.modifiedDate"),
            @Mapping(target="hint", source="hintChannelEntity.refHintHintEntities")
    })
    ChannelItem hintChannelEntityToChannel(HintChannelEntity hintChannelEntity);

    @Mappings({
            @Mapping(target = "hintText", source = "hint.text"),
            @Mapping(target = "hintType", source = "hint.type"),
            @Mapping(target = "modifiedDate", source = "hint.modDate"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "refHintChannelEntity", ignore = true),
    })
    HintHintEntity hintToHintHintEntity(HintItem hint);

    @Mappings({
            @Mapping(target = "text", source = "hintHintEntity.hintText"),
            @Mapping(target = "type", source = "hintHintEntity.hintType"),
            @Mapping(target = "modDate", source = "hintHintEntity.modifiedDate")
    })
    HintItem hintHintEntityToHint(HintHintEntity hintHintEntity);

    @Mappings({
            @Mapping(target = "modifiedDate", source = "businessOpItem.modDate"),
            @Mapping(target = "refHintBusinessStepEntities", source = "businessOpItem.step"),
            @Mapping(target = "refHintChannelEntity", source = "businessOpItem.channel"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "refHintUserEntities", ignore = true),
            @Mapping(target = "userId", ignore = true)
    })
    HintBusinessOpEntity businessOpItemToHintBusinessOpEntity(BusinessOp businessOpItem);

    List<HintBusinessOpEntity> businessOpItemToHintBusinessOpEntity(List<BusinessOp> businessOpItem);

    @Mappings({
            @Mapping(target = "modDate", source = "hintBusinessOpEntity.modifiedDate"),
            @Mapping(target = "step", source = "hintBusinessOpEntity.refHintBusinessStepEntities"),
            @Mapping(target = "channel", source = "hintBusinessOpEntity.refHintChannelEntity")
    })
    BusinessOp hintBusinessOpEntityToBusinessOpItem(HintBusinessOpEntity hintBusinessOpEntity);

    List<BusinessOp> hintBusinessOpEntityToBusinessOpItem(List<HintBusinessOpEntity> hintBusinessOpEntity);

    @Mappings({
            @Mapping(target = "modifiedDate", source = "stepItem.modDate"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "refHintBusinessOpEntity", ignore = true),
            @Mapping(target = "businessOpId", ignore = true),
    })
    HintBusinessStepEntity hintBusinessStepEntityToStepItem(StepItem stepItem);

    List<HintBusinessStepEntity> hintBusinessStepEntityToStepItem(List<StepItem> stepItem);

    @Mappings({
            @Mapping(target = "modDate", source = "hintBusinessStepEntity.modifiedDate"),
            @Mapping(target = "businessCode", ignore = true)
    })
    StepItem stepItemToHintBusinessStepEntity(HintBusinessStepEntity hintBusinessStepEntity);

    List<StepItem> stepItemToHintBusinessStepEntity(List<HintBusinessStepEntity> hintBusinessStepEntity);

    @Mappings({
            @Mapping(target = "createDate", source = "userItem.createDate"),
            @Mapping(target = "modifiedDate", source = "userItem.modDate"),
            @Mapping(target = "refHintBusinessOpEntity", source = "userItem.businessOp"),
            @Mapping(target = "id", ignore = true),
    })
    HintUserEntity hintUserEntityToUserItem(User userItem);

    @Mappings({
            @Mapping(target = "createDate", source = "hintUserEntity.createDate"),
            @Mapping(target = "modDate", source = "hintUserEntity.modifiedDate"),
            @Mapping(target = "businessOp", source = "hintUserEntity.refHintBusinessOpEntity")
    })
    User userItemToHintUserEntity(HintUserEntity hintUserEntity);

}
