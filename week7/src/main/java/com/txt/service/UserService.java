package com.txt.service;

import com.txt.datasource.EnumDataSourceType;
import com.txt.datasource.TargetDataSource;
import com.txt.entity.User;
import com.txt.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description :
 * @Date : 2022-02-27
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @TargetDataSource(EnumDataSourceType.WRITE)
    public boolean addUser(User user) {
        int result = userMapper.insert(user);
        if (result <= 0) {
            throw new RuntimeException("exception");
        }
        return result > 0;
    }

    @TargetDataSource(EnumDataSourceType.READ)
    public List<User> queryUser() {
        return userMapper.selectList(null);
    }
}
