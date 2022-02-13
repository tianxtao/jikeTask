package com.txt.homework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description :
 * @Date : 2022-02-11
 */
public class Main01 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        School school1 = (School) context.getBean("school1-1");
        school1.publish();
        System.out.println("   context.getBeanDefinitionNames() ===>> "+ String.join(",", context.getBeanDefinitionNames()));
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        System.out.println("   context.getBeanDefinitionNames() ===>> "+ String.join(",", applicationContext.getBeanDefinitionNames()));
        School school2 = (School) applicationContext.getBean("school");
        school2.publish();
        Teacher teacher = (Teacher) context.getBean("teacher");
        teacher.publish();
    }
}
