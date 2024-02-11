package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    @PostConstruct
    public void loadData(){
        theStudents =new ArrayList<>();
        this.theStudents.add(new Student("Poornima", "Patel"));
        this.theStudents.add(new Student("Mario", "Rossi"));
        this.theStudents.add(new Student("Mary", "smith"));
    }

    // define endpoint for"/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents(){

        return this.theStudents;
    }

    //define endpoint or "/students/ {studentId}" -return student at index
    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        // just index into the list... keep it simple for now

        //check the studentId again list size

        if(studentId >= theStudents.size() || studentId<0){
            throw  new StudentNotFoundException("Student id not found - "+ studentId);
        }

        return theStudents.get(studentId);
    }


}
