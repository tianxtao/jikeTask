package com.txt.homework2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description :
 * @Date : 2022-02-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class School {
    private String name;
    private List<Klass> Klasss;
    private Student student;
    public void publish(){
        StringBuilder stringBuilder  = new StringBuilder(name + "班级信息：\n");
        for(Klass klass : Klasss) {
            stringBuilder.append(klass.classInfo()).append("\n");
        }
        System.out.println(stringBuilder.toString());
        System.out.println("优秀学生代表：" + student.info());
    }
}
