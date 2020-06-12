package avakhidov.services;

import avakhidov.data.BaseEvent;
import avakhidov.data.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = {"NIGHT"})
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;

    @Autowired
    private TestData testData;

    @Test
    public void createCommonEvents() {
        List<BaseEvent> baseEvents = testData.createBaseEvents();

        List<String> commonEvents = commonService.createCommonEvents(baseEvents);

        assertEquals(2, commonEvents.size());

    }

    @Test
    public void createCommonAction() {
        List<BaseEvent> baseEvents = testData.createBaseEvents();

        Map< UUID, List<String>> commonEvents = commonService.createCommonAction(baseEvents);

        assertEquals(1, commonEvents.size());

    }
}