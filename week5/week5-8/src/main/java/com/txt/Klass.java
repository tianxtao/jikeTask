package com.txt;

import lombok.Data;

import java.util.List;

@Data
public class Klass {
    private String name;
    
    List<Student> students;
    
    public void dong(){
        System.out.println("name=" + name);
        System.out.println(this.getStudents());
    }
    
}
