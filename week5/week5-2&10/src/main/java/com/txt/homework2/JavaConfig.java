package com.txt.homework2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description :
 * @Date : 2022-02-11
 */
@Configuration
//@ComponentScan(basePackageClasses={School.class,Student.class, Klass.class})
public class JavaConfig {
    @Bean
    public Student student(){
        Student student = new Student();
        student.setSn(2201);
        student.setName("漩涡鸣人");
        Klass klass = new Klass();
        klass.setName("第七班");
        student.setKlass(klass);
        return student;
    }

    @Bean
    public Klass klass() {
        Klass klass = new Klass();
        klass.setName("第七班");
        List<Student> students = new ArrayList<>();
        students.add(student());
        Student student = new Student();
        student.setSn(2202);
        student.setName("宇智波佐助");
        Klass klass1 = new Klass();
        klass1.setName("第七班");
        student.setKlass(klass);
        student.setKlass(klass1);
        students.add(student);
        klass.setStudents(students);
        return klass;
    }

    @Bean
    public School school(Student student) {
        School school = new School();
        school.setStudent(student());
        school.setName("忍者学校");
        List<Klass> list = new ArrayList<>();
        list.add(klass());
        school.setKlasss(list);
        return school;
    }

}
