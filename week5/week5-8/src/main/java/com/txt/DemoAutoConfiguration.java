package com.txt;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description :
 * @Date : 2022-02-12
 */
@Configuration
@RequiredArgsConstructor
@EnableAspectJAutoProxy
@EnableConfigurationProperties({StudentProperties.class, KlassProperties.class, SchoolProperties.class})
public class DemoAutoConfiguration {
    private final StudentProperties studentProperties;
    private final KlassProperties klassProperties;
    private final SchoolProperties schoolProperties;

    @Bean
    @ConditionalOnMissingBean(Student.class)
    public Student student() {
        return Student.create();
    }

    @Bean
    @ConditionalOnMissingBean(Klass.class)
    public Klass klass() {
        Klass klass = new Klass();
        klass.setName(klassProperties.getName1());
        Student student1 = new Student();
        student1.setId(studentProperties.getId1());
        student1.setName(studentProperties.getName1());
        Student student2 = new Student();
        student2.setId(studentProperties.getId2());
        student2.setName(studentProperties.getName2());
        Student student3 = new Student();
        student3.setId(studentProperties.getId3());
        student3.setName(studentProperties.getName3());
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        klass.setStudents(studentList);
        return klass;
    }

    @Bean
    @ConditionalOnMissingBean(School.class)
    public School school() {
        School school = new School();
        school.setName(schoolProperties.getName());
        school.setKlass(klass());
        school.setStudent(student());
        return school;
    }

    @Bean
    public Aop2 aop2() {
        return new Aop2();
    }
}
