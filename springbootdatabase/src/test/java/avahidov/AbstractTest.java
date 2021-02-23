package avahidov;

import avahidov.entities.HintBusinessOpEntity;
import avahidov.entities.HintBusinessStepEntity;
import avahidov.entities.HintChannelEntity;
import avahidov.entities.HintHintEntity;
import avahidov.entities.HintUserEntity;
import avahidov.uservo.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public abstract class AbstractTest {

    private static final String USER_HP = "user_HP";
    private static final String TITLE_OP_HP = "title_op_HP_";
    private static final String CODE_OP_HP = "code_Op_HP_";
    private static final String TITLE = "Title_";
    private static final String CREATED = "created";
    private static final String TEST = "test";

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

    LocalDateTime time;
    int dayOfYear;
    int second;
    int day;
    int nano;
    String uuid;

    @BeforeEach
    public void createData() {
        time = LocalDateTime.now();
        dayOfYear = time.getDayOfYear();
        second = time.getSecond();
        day = time.getDayOfMonth();
        nano = time.getNano();
        uuid = String.valueOf(UUID.randomUUID());
    }

    public HintUserEntity generateCustomTimeUserEntity() {
        SimpleDateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy+HH:ss");
        SimpleDateFormat dateForSimple = new SimpleDateFormat("dd-MM-yyyy");

        HintUserEntity userEntity = new HintUserEntity();
        userEntity.setCreateDate(new Date(new java.util.Date().getTime()));
        userEntity.setModifiedDate(new Date(new java.util.Date().getTime()));
        userEntity.setUser(USER_HP);

        HintBusinessOpEntity businessOpEntity = new HintBusinessOpEntity();
        businessOpEntity.setModifiedDate(new Date(new java.util.Date().getTime()));
        businessOpEntity.setCode(CODE_OP_HP + nano);
        businessOpEntity.setTitle(TITLE_OP_HP + second);
        businessOpEntity.setRefHintUserEntities(userEntity);
        userEntity.setRefHintBusinessOpEntity(businessOpEntity);

        List<HintBusinessStepEntity> stepEntityList = new ArrayList<>();
        HintBusinessStepEntity stepEntity = new HintBusinessStepEntity();
        stepEntity.setCode("code_step_HP_" + nano);
        stepEntity.setModifiedDate(new Date(new java.util.Date().getTime()));
        stepEntity.setTitle(TITLE + dayOfYear);
        stepEntity.setRefHintBusinessOpEntity(businessOpEntity);
        stepEntityList.add(stepEntity);

        HintBusinessStepEntity stepEntity2 = new HintBusinessStepEntity();
        stepEntity2.setCode("code2_step_HP_" + nano);
        stepEntity2.setModifiedDate(new Date(new java.util.Date().getTime()));
        stepEntity2.setTitle(TITLE + dayOfYear);
        stepEntity2.setRefHintBusinessOpEntity(businessOpEntity);
        stepEntityList.add(stepEntity2);
        businessOpEntity.setRefHintBusinessStepEntities(stepEntityList);

        List<HintChannelEntity> stepChannelList = new ArrayList<>();
        HintChannelEntity channelEntity = new HintChannelEntity();
        channelEntity.setCode("code_channel_HP_" + nano);
        channelEntity.setModifiedDate(new Date(new java.util.Date().getTime()));
        channelEntity.setRefHintBusinessOpEntities(businessOpEntity);
        channelEntity.setTitle(TITLE + dayOfYear);
        stepChannelList.add(channelEntity);

        HintChannelEntity channelEntity2 = new HintChannelEntity();
        channelEntity2.setCode("code2_channel_HP_" + nano);
        channelEntity2.setModifiedDate(new Date(new java.util.Date().getTime()));
        channelEntity2.setRefHintBusinessOpEntities(businessOpEntity);
        channelEntity2.setTitle(TITLE + dayOfYear);
        stepChannelList.add(channelEntity2);

        businessOpEntity.setRefHintChannelEntity(stepChannelList);

        HintHintEntity hintEntity = new HintHintEntity();
        List<HintHintEntity> hintEntityList = new ArrayList<>();
        hintEntity.setCode("code_hint_HP_" + nano);
        hintEntity.setHintText("text_hint_" + uuid);
        hintEntity.setHintType(TEST);
        hintEntity.setModifiedDate(new Date(new java.util.Date().getTime()));
        hintEntity.setPilot(day % 2 == 0);
        hintEntity.setStatus(CREATED);
        hintEntity.setrefHintChannelEntity(channelEntity);
        hintEntityList.add(hintEntity);

        HintHintEntity hintEntity2 = new HintHintEntity();
        hintEntity2.setCode("code2_hint_HP_" + nano);
        hintEntity2.setHintText("text_hint_" + uuid);
        hintEntity2.setHintType(TEST);
        hintEntity2.setModifiedDate(new Date(new java.util.Date().getTime()));
        hintEntity2.setPilot(day % 2 == 0);
        hintEntity2.setStatus(CREATED);
        hintEntity2.setrefHintChannelEntity(channelEntity);
        hintEntityList.add(hintEntity2);

        channelEntity.setRefHintHintEntities(hintEntityList);

        List<HintHintEntity> hintEntityList2 = new ArrayList<>();
        HintHintEntity hintEntity3 = new HintHintEntity();
        hintEntity3.setCode("code3_hint_HP_" + nano);
        hintEntity3.setHintText("text_hint_" + uuid);
        hintEntity3.setHintType(TEST);
        hintEntity3.setModifiedDate(new Date(new java.util.Date().getTime()));
        hintEntity3.setPilot(day % 2 == 0);
        hintEntity3.setStatus(CREATED);
        hintEntity3.setrefHintChannelEntity(channelEntity2);
        hintEntityList2.add(hintEntity3);

        HintHintEntity hintEntity4 = new HintHintEntity();
        hintEntity4.setCode("code4_hint_HP_" + nano);
        hintEntity4.setHintText("text_hint_" + uuid);
        hintEntity4.setHintType(TEST);
        hintEntity4.setModifiedDate(new Date(new java.util.Date().getTime()));
        hintEntity4.setPilot(day % 2 == 0);
        hintEntity4.setStatus(CREATED);
        hintEntity4.setrefHintChannelEntity(channelEntity2);
        hintEntityList2.add(hintEntity4);
        channelEntity2.setRefHintHintEntities(hintEntityList2);

        return userEntity;
    }
}
