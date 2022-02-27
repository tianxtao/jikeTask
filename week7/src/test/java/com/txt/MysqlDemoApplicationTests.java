package com.txt;

import com.txt.entity.User;
import com.txt.mapper.UserMapper;
import com.txt.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MysqlDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        User users = new User();
        users.setUsername("aaa");
        users.setPhone("1558722");
        users.setPassword("1234566");
        userMapper.insert(users);
        List<User> userList = userMapper.selectList(null);
        for(User user:userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testDataSource() {
        User user = new User();
        user.setUsername("qqqq");
        user.setPhone("156456461");
        user.setPassword("1123644");
        System.out.println(userService.addUser(user));

        List<User> userList = userService.queryUser();
        for(User user1 :userList) {
            System.out.println(user1);
        }
    }
}
