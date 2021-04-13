package avahidov.controllers;

import avahidov.AbstractTest;
import avahidov.uservo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HintUserControllerTest extends AbstractTest {

    @Autowired
    ObjectMapper mapper;

    @Test
    void createUserList() {

        var count = 0;
        var userList = new ArrayList<>();
        do {
            var user = generateCustomTimeUser();
            userList.add(user);
            count++;
        } while (count < 5);

        HttpEntity<List<User>> httpEntity = new HttpEntity(userList);

        ResponseEntity<List<User>> responseEntity = this.restTemplate.exchange("http://localhost:" + port +
                "/avahidov/hint/user-hints",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<List< User >>(){});

        assertNotNull(responseEntity.getBody());

        List<User> entityBodyList = responseEntity.getBody();
        assertEquals(5, entityBodyList.size());
    }

    @Test
    void createFileTestData() {
        var count = 0;
        var userList = new ArrayList<>();
        do {
            var user = generateCustomTimeUser();
            userList.add(user);
            count++;
        } while (count < 5);

        var generatedTestData = new File("Stub");
        try {
            generatedTestData = new File("src/test/resources/generate_user_list.json");
            mapper.writeValue(generatedTestData, userList );
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(generatedTestData);
        assertEquals("generate_user_list.json", generatedTestData.getName());
    }
}