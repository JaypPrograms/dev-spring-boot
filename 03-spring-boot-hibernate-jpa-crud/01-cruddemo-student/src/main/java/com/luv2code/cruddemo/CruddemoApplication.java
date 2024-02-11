package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//  createStudent(studentDAO);
			//	createMultipleStudents(studentDAO);
			//	readStudent(studentDAO);
			// queryStudents(studentDAO);
			// queryForStudentsByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			// deleteAllStudent(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numRowsDeleted=studentDAO.deleteAll();
		System.out.println("Deleted row count: "+numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId=3;
		System.out.println("Deleting student id: " + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {

		//retrieve student based on the id: primary key
		int studentId=4;
		System.out.println("Getting student with id: " + studentId);
		Student theStudent=studentDAO.findById(studentId);

		//change first name "Scooby"
		System.out.println("Updating student....");
		theStudent.setLastName("Duck");

		//update the student
		studentDAO.update(theStudent);

		//display the updated student
		System.out.println("Updated student: "+ theStudent);

	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {

		// get a list of students
		List<Student> theStudents=studentDAO.findByLastName("Public");

		//display list of students
		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents=studentDAO.findAll();

		//display list of students
		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		//create a student object
		System.out.println("Creating new student object....");
		Student tempStudent= new Student("Daffy","Duffy", "daffy@luv2code.com");

		//save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		//display id of the saved student
		int id=tempStudent.getId();
		System.out.println("Saved student. Generated id: " + id);

		//retrieve student based on the id: primary key
		System.out.println("Retrieving the student with id: "+id);
		Student myStudent= studentDAO.findById(id);

		//display student
		System.out.println("Found the student: "+myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// create multiple students

		System.out.println("Creating new student object....");
		Student tempStudent1= new Student("john","Doe", "john@luv2code.com");
		Student tempStudent2= new Student("Mary","Public", "mary@luv2code.com");
		Student tempStudent3= new Student("Bonita","Applebum", "bonita@luv2code.com");

		//save the student objects
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object....");
		Student tempStudent= new Student("Paul","Doe", "paul@luv2code.com");

		//save the student object
		System.out.println("Saving the student....");
		studentDAO.save(tempStudent);

		//display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
