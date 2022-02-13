package com.txt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
public class School implements ISchool {
    private String name;

    private Klass klass;

    private Student student;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + this.klass.getStudents().size() + " students and one is " + this.student);
        
    }
    
}
