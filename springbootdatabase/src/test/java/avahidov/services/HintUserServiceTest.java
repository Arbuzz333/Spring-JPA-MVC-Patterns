package avahidov.services;

import avahidov.AbstractTest;
import avahidov.entities.HintUserEntity;
import avahidov.uservo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;





@SpringBootTest
class HintUserServiceTest extends AbstractTest{

    @Autowired
    HintUserService hintUserService;

    @Test
    void saveCustomUser() {
        HintUserEntity userEntity = generateCustomTimeUserEntity();

        HintUserEntity userSaved = hintUserService.saveToDataBase(userEntity);

        assertNotNull(userSaved);
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