package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			deleteInstructorDetail(appDAO);
		};
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
