package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner-> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
//			deleteInstructorDetail(appDAO);
//			createInstructorWithCourses(appDAO);

//			findInstructorWithCourses(appDAO);

//			findCoursesForInstructor(appDAO);
			
//			findInstructorWithCoursesJoinFetch(appDAO);

//			updateInstructor(appDAO);

//			updateCourse(appDAO);

//			deleteInstructor(appDAO);

			deleteCourse(appDAO);
		};
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId=10;

		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");

	}

	private void updateCourse(AppDAO appDAO) {
		int theId=10;

		System.out.println("Find course by id: " +theId);
		Course tempCourse=appDAO.findCourseById(theId);

		System.out.println("Updating course id: " + theId);
		tempCourse.setTitle("Enjoy the Simple Things");

		appDAO.update(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId=1;

		//find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);

		//update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setEmail("jayp@luv2code.com");

		appDAO.update(tempInstructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId=1;

		System.out.println("Finding instructor id: "+ theId);

		Instructor tempInstructor= appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " +tempInstructor);
		System.out.println("the associated courses: "+ tempInstructor.getCourses());

		System.out.println("done!");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int theId=1;

		System.out.println("Finding instructor id: "+ theId);

		Instructor tempInstructor=appDAO.findInstructorById(theId);
		List<Course> courses= appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourses(courses);

		System.out.println("tempInstructor: " +tempInstructor);
		System.out.println("the associated courses: "+ tempInstructor.getCourses());

		System.out.println("done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId=1;

		System.out.println("Finding instructor id: "+ theId);

		Instructor tempInstructor=appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " +tempInstructor);
		System.out.println("the associated courses: "+ tempInstructor.getCourses());

		System.out.println("done!");
	}

	public void createInstructorWithCourses(AppDAO appDAO){

		Instructor tempInstructor =
				new Instructor("Susan", "Public",
						"susan@luv2code.com");

		InstructorDetail tempInstructorDetail= new InstructorDetail(
				"http://www.youtube.com",
				"Video Games");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//create some courses

		Course tempCourse1= new Course("Aid Guitar- The ultimate Guide");
		Course tempCourse2= new Course("The Pinball Masterclass");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: "+ tempInstructor.getCourses());

		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId=3;


		System.out.println("Deleting the instructorDetail by Id: "+ theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId=2;

		System.out.println("Find InstructorDetail by id: " + theId);
		InstructorDetail tempInstructorDetail=appDAO.findInstructorDetailById(theId);
		System.out.println("TempInstructorDetail: "+tempInstructorDetail);
		System.out.println("The associated instructor: "+tempInstructorDetail.getInstructor());
		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId=1;

		System.out.println("Deleting the instructor by Id: "+ theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int id=2;
		System.out.println("Finding instructor id: " + id);
		Instructor tempInstructor= appDAO.findInstructorById(id);

		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associate instructorDetail only: "+ tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		//create the instructor
		Instructor tempInstructor =
				new Instructor("Madhu", "Patel",
						"madhu@luv2code.com");

		InstructorDetail tempInstructorDetail= new InstructorDetail(
				"http://luv2code.com/youtube",
				"Guitar");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Saving the instructor....");
		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}
}
