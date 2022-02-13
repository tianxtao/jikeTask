package com.txt.homework2;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    // 学号
    private int sn;

    // 姓名
    private String name;

    // 班级
    private Klass klass;

    public String info() {
        String info = klass.getName() + " " + name + " 学号：" + sn;
        return info;
    }
}
