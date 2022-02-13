package com.txt.week502test;

import com.txt.Klass;
import com.txt.School;
import com.txt.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description :
 * @Date : 2022-02-12
 */
@SpringBootTest
public class Week502Test01 {
    @Autowired
    private School school;
    @Autowired
    private Student student;
    @Autowired
    private Klass klass;

    @Test
    public void test01() {
        school.ding();
        student.print();
        klass.dong();
    }
}
