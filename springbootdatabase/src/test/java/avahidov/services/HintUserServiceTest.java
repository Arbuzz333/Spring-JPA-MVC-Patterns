package avahidov.services;

import avahidov.AbstractTest;
import avahidov.entities.HintBusinessOpEntity;
import avahidov.entities.HintBusinessStepEntity;
import avahidov.entities.HintChannelEntity;
import avahidov.entities.HintHintEntity;
import avahidov.entities.HintUserEntity;
import avahidov.uservo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;





@SpringBootTest
class HintUserServiceTest {

    @Autowired
    HintUserService hintUserService;

    @Test
    void saveCustomUser() {
        HintUserEntity userEntity = new HintUserEntity();
        userEntity.setCreateDate(new Date(5557777888L));
        userEntity.setModifiedDate(new Date(5557777888L));
        userEntity.setUser("user" + new java.util.Date());

        HintBusinessOpEntity businessOpEntity = new HintBusinessOpEntity();
        businessOpEntity.setModifiedDate(new Date(5557777888L));
        businessOpEntity.setCode("code_HintBusinessOpEntity" + new java.util.Date());
        businessOpEntity.setRefHintUserEntities(userEntity);
        userEntity.setRefHintBusinessOpEntity(businessOpEntity);

        HintBusinessStepEntity stepEntity = new HintBusinessStepEntity();
        stepEntity.setCode("code_step_" + new java.util.Date());
        stepEntity.setModifiedDate(new Date(5557777888L));
        stepEntity.setRefHintBusinessOpEntity(businessOpEntity);
        businessOpEntity.setRefHintBusinessStepEntities(Collections.singletonList(stepEntity));

        HintChannelEntity channelEntity = new HintChannelEntity();
        channelEntity.setCode("code_channel_" + new java.util.Date());
        channelEntity.setModifiedDate(new Date(5557777888L));
        channelEntity.setRefHintBusinessOpEntities(businessOpEntity);
        channelEntity.setTitle("Title_" + new java.util.Date());
        businessOpEntity.setRefHintChannelEntity(Collections.singletonList(channelEntity));

        HintHintEntity hintEntity = new HintHintEntity();
        hintEntity.setCode("code_hint_" + new java.util.Date());
        hintEntity.setHintText("text_hint_" + new java.util.Date());
        hintEntity.setModifiedDate(new Date(new java.util.Date().getTime()));
        hintEntity.setPilot(false);
        hintEntity.setStatus("test");
        hintEntity.setrefHintChannelEntity(channelEntity);
        channelEntity.setRefHintHintEntities(Collections.singletonList(hintEntity));

        HintUserEntity hintUserEntitySaved = hintUserService.saveToDataBase(userEntity);

        assertNotNull(hintUserEntitySaved);
    }

    @Test
    void saveToDataBase() {
        User user = AbstractTest.readTestData();

        HintUserEntity userSaved = hintUserService.saveToDataBase(user);
        hintUserService.deleteFromDataBase(userSaved);

        assertNotNull(userSaved.getId());
        assertNotNull(userSaved.getRefHintBusinessOpEntity().getId());

        userSaved.getRefHintBusinessOpEntity().getRefHintBusinessStepEntities()
                .forEach(step ->
                        assertNotNull(step.getId()));

        userSaved.getRefHintBusinessOpEntity().getRefHintChannelEntity()
                .forEach(channel ->
                        assertNotNull(channel.getId()));

        userSaved.getRefHintBusinessOpEntity().getRefHintChannelEntity()
                .forEach(channel -> channel.getRefHintHintEntities().forEach(hint ->
                        assertNotNull(hint.getId()))
                );
    }
}