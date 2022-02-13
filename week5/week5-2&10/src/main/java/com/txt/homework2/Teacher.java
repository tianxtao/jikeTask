package com.txt.homework2;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Description :
 * @Date : 2022-02-11
 */
@Data
@Component
public class Teacher {
    public void publish() {
        System.out.println("我是老师");
    }
}
