package avahidov;

import avahidov.entities.HintBusinessOpEntity;
import avahidov.entities.HintBusinessStepEntity;
import avahidov.entities.HintChannelEntity;
import avahidov.entities.HintHintEntity;
import avahidov.entities.HintUserEntity;
import avahidov.uservo.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractTest {

    public static User readTestData() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);

        File file = new File("src/test/resources/test_data_1.json");

        User user = new User();
        try {
            user = objectMapper.readValue(file, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static HintUserEntity getCustomTimeUserEntity() {
        SimpleDateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy+HH:ss");
        SimpleDateFormat dateForSimple = new SimpleDateFormat("dd-MM-yyyy");

        HintUserEntity userEntity = new HintUserEntity();
        userEntity.setCreateDate(new Date(new java.util.Date().getTime()));
        userEntity.setModifiedDate(new Date(new java.util.Date().getTime()));
        userEntity.setUser("user_HP");

        HintBusinessOpEntity businessOpEntity = new HintBusinessOpEntity();
        businessOpEntity.setModifiedDate(new Date(new java.util.Date().getTime()));
        businessOpEntity.setCode("code_Op_" + dateFor.format(new java.util.Date()));
        businessOpEntity.setTitle("business_op_" + dateForSimple.format(new java.util.Date()));
        businessOpEntity.setRefHintUserEntities(userEntity);
        userEntity.setRefHintBusinessOpEntity(businessOpEntity);

        List<HintBusinessStepEntity> stepEntityList = new ArrayList<>();
        HintBusinessStepEntity stepEntity = new HintBusinessStepEntity();
        stepEntity.setCode("code_step_" + dateFor.format(new java.util.Date()));
        stepEntity.setModifiedDate(new Date(new java.util.Date().getTime()));
        stepEntity.setTitle("title_" + dateForSimple.format(new java.util.Date()));
        stepEntity.setRefHintBusinessOpEntity(businessOpEntity);
        stepEntityList.add(stepEntity);

        HintBusinessStepEntity stepEntity2 = new HintBusinessStepEntity();
        stepEntity2.setCode("code2_step_" + dateFor.format(new java.util.Date()));
        stepEntity2.setModifiedDate(new Date(new java.util.Date().getTime()));
        stepEntity2.setTitle("title_" + dateForSimple.format(new java.util.Date()));
        stepEntity2.setRefHintBusinessOpEntity(businessOpEntity);
        stepEntityList.add(stepEntity2);
        businessOpEntity.setRefHintBusinessStepEntities(stepEntityList);

        List<HintChannelEntity> stepChannelList = new ArrayList<>();
        HintChannelEntity channelEntity = new HintChannelEntity();
        channelEntity.setCode("code_channel_" + dateFor.format(new java.util.Date()));
        channelEntity.setModifiedDate(new Date(new java.util.Date().getTime()));
        channelEntity.setRefHintBusinessOpEntities(businessOpEntity);
        channelEntity.setTitle("Title_" + dateForSimple.format(new java.util.Date()));
        stepChannelList.add(channelEntity);

        HintChannelEntity channelEntity2 = new HintChannelEntity();
        channelEntity2.setCode("code2_channel_" + dateFor.format(new java.util.Date()));
        channelEntity2.setModifiedDate(new Date(new java.util.Date().getTime()));
        channelEntity2.setRefHintBusinessOpEntities(businessOpEntity);
        channelEntity2.setTitle("Title_" + dateForSimple.format(new java.util.Date()));
        stepChannelList.add(channelEntity2);
        businessOpEntity.setRefHintChannelEntity(stepChannelList);

        int day = Integer.parseInt(dateForSimple.format(new java.util.Date()).split("-")[0]);

        HintHintEntity hintEntity = new HintHintEntity();
        List<HintHintEntity> hintEntityList = new ArrayList<>();
        hintEntity.setCode("code_hint_" + dateFor.format(new java.util.Date()));
        hintEntity.setHintText("text_hint_" + dateForSimple.format(new java.util.Date()));
        hintEntity.setHintType("type_" + dateForSimple.format(new java.util.Date()));
        hintEntity.setModifiedDate(new Date(new java.util.Date().getTime()));
        hintEntity.setPilot(day % 2 == 0);
        hintEntity.setStatus("test_" + day);
        hintEntity.setrefHintChannelEntity(channelEntity);
        hintEntityList.add(hintEntity);

        HintHintEntity hintEntity2 = new HintHintEntity();
        hintEntity2.setCode("code2_hint_" + dateFor.format(new java.util.Date()));
        hintEntity2.setHintText("text_hint_" + dateForSimple.format(new java.util.Date()));
        hintEntity2.setHintType("type_" + dateForSimple.format(new java.util.Date()));
        hintEntity2.setModifiedDate(new Date(new java.util.Date().getTime()));
        hintEntity2.setPilot(day % 2 == 0);
        hintEntity2.setStatus("test_" + day);
        hintEntity2.setrefHintChannelEntity(channelEntity);
        hintEntityList.add(hintEntity2);
        channelEntity.setRefHintHintEntities(hintEntityList);

        List<HintHintEntity> hintEntityList2 = new ArrayList<>();
        HintHintEntity hintEntity3 = new HintHintEntity();
        hintEntity3.setCode("code3_hint_" + dateFor.format(new java.util.Date()));
        hintEntity3.setHintText("text_hint_" + dateForSimple.format(new java.util.Date()));
        hintEntity3.setHintType("type_" + dateForSimple.format(new java.util.Date()));
        hintEntity3.setModifiedDate(new Date(new java.util.Date().getTime()));
        hintEntity3.setPilot(day % 2 == 0);
        hintEntity3.setStatus("test_" + day);
        hintEntity3.setrefHintChannelEntity(channelEntity2);
        hintEntityList2.add(hintEntity3);

        HintHintEntity hintEntity4 = new HintHintEntity();
        hintEntity4.setCode("code4_hint_" + dateFor.format(new java.util.Date()));
        hintEntity4.setHintText("text_hint_" + dateForSimple.format(new java.util.Date()));
        hintEntity4.setHintType("type_" + dateForSimple.format(new java.util.Date()));
        hintEntity4.setModifiedDate(new Date(new java.util.Date().getTime()));
        hintEntity4.setPilot(day % 2 == 0);
        hintEntity4.setStatus("test_" + day);
        hintEntity4.setrefHintChannelEntity(channelEntity2);
        hintEntityList2.add(hintEntity4);
        channelEntity2.setRefHintHintEntities(hintEntityList2);

        return userEntity;
    }
}
