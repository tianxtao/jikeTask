package com.txt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description :
 * @Date : 2022-02-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "t_user")
public class User {
    private String username;
    private String phone;
    private String password;
}
