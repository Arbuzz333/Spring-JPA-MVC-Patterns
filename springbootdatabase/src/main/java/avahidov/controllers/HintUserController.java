package avahidov.controllers;


import avahidov.services.HintUserService;
import avahidov.uservo.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/hint")
public class HintUserController {

    @Autowired
    HintUserService hintUserService;

    @PostMapping("/user-hints")
    @ApiOperation(value = "Creation user hints")
    public List<User> createUserList(@RequestBody List<User> userList) {

        List<User> savedUserList = hintUserService.createUserList(userList);

        return savedUserList;
    }

}
