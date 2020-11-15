package avahidov;

import avahidov.uservo.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;



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
}
