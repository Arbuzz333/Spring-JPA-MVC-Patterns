package avahidov.mappers;

import avahidov.AbstractTest;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;




@SpringBootTest
class HintChannelEntityChanelMapperTest {

    @Autowired
    HintChannelEntityChanelMapper mapper;

    @Test
    void userToUserEntityJsonTest() {
        User user = AbstractTest.readTestData();

        String createDate = user.getCreateDate();
        BusinessOp businessOp = user.getBusinessOp();
        List<StepItem> stepItemList = businessOp.getStep();
        List<ChannelItem> channelItemList = businessOp.getChannel();
        List<HintItem> hintMB = channelItemList.get(0).getHint();
        List<HintItem> hintWEB = channelItemList.get(1).getHint();
        List<HintItem> hintFILE = channelItemList.get(2).getHint();

        assertEquals("29-10-2020", createDate);
        assertEquals("investment_consent", businessOp.getTitle());
        assertEquals("MB", channelItemList.get(0).getCode() );
        assertEquals("WEB", channelItemList.get(1).getCode() );
        assertEquals("FILE", channelItemList.get(2).getCode() );
        assertEquals("hint_consent_pif", hintMB.get(0).getCode() );
        assertEquals("hint_consent_shares", hintMB.get(1).getCode() );

        assertEquals("hint_consent_mortgage", hintWEB.get(0).getCode() );
        assertEquals("hint_consent_leasing", hintWEB.get(1).getCode() );

        assertEquals("hint_consent_rent", hintFILE.get(0).getCode() );
        assertEquals("hint_consent_sale", hintFILE.get(1).getCode() );

        assertEquals("create", stepItemList.get(0).getCode());
        assertEquals("initialization", stepItemList.get(1).getCode());
        assertEquals("prolongation", stepItemList.get(2).getCode());
        assertEquals("close", stepItemList.get(3).getCode());
    }

    @Test
    void userToUserEntityMapperTest() {
        User user = AbstractTest.readTestData();

        HintUserEntity hintUserEntity = mapper.hintUserEntityToUserItem(user);
        assertNotNull(hintUserEntity);

        Date createDate = hintUserEntity.getCreateDate();
        HintBusinessOpEntity businessOp = hintUserEntity.getRefHintBusinessOpEntity();
        List<HintBusinessStepEntity> stepItemList = businessOp.getRefHintBusinessStepEntities();
        List<HintChannelEntity> channelItemList = businessOp.getRefHintChannelEntity();
        List<HintHintEntity> hintMB = channelItemList.get(0).getRefHintHintEntities();
        List<HintHintEntity> hintWEB = channelItemList.get(1).getRefHintHintEntities();
        List<HintHintEntity> hintFILE = channelItemList.get(2).getRefHintHintEntities();

        assertEquals("2020-10-29", createDate.toString());
        assertEquals("investment_consent", businessOp.getTitle());
        assertEquals("MB", channelItemList.get(0).getCode() );
        assertEquals("WEB", channelItemList.get(1).getCode() );
        assertEquals("FILE", channelItemList.get(2).getCode() );
        assertEquals("hint_consent_pif", hintMB.get(0).getCode() );
        assertEquals("hint_consent_shares", hintMB.get(1).getCode() );

        assertEquals("hint_consent_mortgage", hintWEB.get(0).getCode() );
        assertEquals("hint_consent_leasing", hintWEB.get(1).getCode() );

        assertEquals("hint_consent_rent", hintFILE.get(0).getCode() );
        assertEquals("hint_consent_sale", hintFILE.get(1).getCode() );

        assertEquals("create", stepItemList.get(0).getCode());
        assertEquals("initialization", stepItemList.get(1).getCode());
        assertEquals("prolongation", stepItemList.get(2).getCode());
        assertEquals("close", stepItemList.get(3).getCode());
    }

    @Test
    void mapNullTest() {
        Map<Integer, String> integerStringHashMap = new HashMap<>();
        integerStringHashMap.put(null, "eleven");
        integerStringHashMap.put(0, "twelve");
        integerStringHashMap.put(37, null);
        integerStringHashMap.put(17, null);
        integerStringHashMap.put(null, "thirteen");

        assertEquals(4, integerStringHashMap.size());
        assertNull(integerStringHashMap.get(37));
        assertEquals("thirteen", integerStringHashMap.get(null));
        assertEquals("twelve", integerStringHashMap.get(0));
    }

    @Test
    void listTest() {
        List<String> list = new LinkedList<>();
        list.add(null);
        list.add("fifteen");
        list.add(null);
        list.add("fourteen");

        assertEquals(4, list.size());
        assertTrue(list.contains(null));
        list.remove(null);
        assertEquals(3, list.size());
    }

    @Test
    void treeTest() {
        Set<Integer> set = new HashSet<>();
        Set<Integer> setTree = new TreeSet<>();
        Map<Integer, String> map = new TreeMap<>();

        assertThrows(NullPointerException.class, () -> setTree.add(null));

        map.put(45, null);
        map.put(77, null);

        assertFalse(map.size() != 2);
        assertNull(map.get(77));
        assertNull(map.get(45));

        assertThrows(NullPointerException.class, () -> map.put(null, "twenty"));

        set.add(null);

        assertEquals(1, set.size());

        set.add(55);
        set.add(null);
        assertEquals(2, set.size());
    }
}