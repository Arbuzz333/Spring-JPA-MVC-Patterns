package avahidov.mappers;


import avahidov.dao.HintBusinessOpEntity;
import avahidov.dao.HintChannelEntity;
import avahidov.dao.HintHintEntity;
import avahidov.hintvo.BusinessOpItem;
import avahidov.hintvo.Channel;
import avahidov.hintvo.Hint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;



@Mapper(componentModel = "spring")
public interface HintChannelEntityChanelMapper {

    @Mappings({
            @Mapping(target="modifiedDate", source="channel.modDate"),
            @Mapping(target="refHintBusinessOpEntities", source="channel.businessOp"),
            @Mapping(target="refHintBusinessOpEntities", source="channel.hint")
    })
    HintChannelEntity channelToHintChannelEntity(Channel channel);

    @Mappings({
            @Mapping(target="modDate", source="hintChannelEntity.modifiedDate"),
            @Mapping(target="businessOp", source="hintChannelEntity.refHintBusinessOpEntities"),
            @Mapping(target="hint", source="hintChannelEntity.refHintBusinessOpEntities")
    })
    Channel hintChannelEntityToChannel(HintChannelEntity hintChannelEntity);

    HintHintEntity hintToHintHintEntity(Hint hint);

    Hint hintHintEntityToHint(HintHintEntity hintHintEntity);

    HintBusinessOpEntity businessOpItemToHintBusinessOpEntity(BusinessOpItem businessOpItem);

    List<HintBusinessOpEntity> businessOpItemToHintBusinessOpEntity(List<BusinessOpItem> businessOpItem);

    BusinessOpItem hintBusinessOpEntityToBusinessOpItem(HintBusinessOpEntity hintBusinessOpEntity);

    List<BusinessOpItem> hintBusinessOpEntityToBusinessOpItem(List<HintBusinessOpEntity> hintBusinessOpEntity);
}
