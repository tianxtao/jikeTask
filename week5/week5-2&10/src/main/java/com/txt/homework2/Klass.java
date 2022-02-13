package com.txt.homework2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Klass {
    // 班级名称
    private String name;
    // 班级里的学生
    List<Student> students;

    public String classInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + "有" + students.size() + "个学生。【");
        for(Student student : students) {
            stringBuilder.append(student.info()).append(";");
        }
        stringBuilder.append("】");

        return stringBuilder.toString();
    }
}
