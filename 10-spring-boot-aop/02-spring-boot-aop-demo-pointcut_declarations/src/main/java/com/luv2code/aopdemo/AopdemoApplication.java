package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO,
											   MembershipDAO membershipDAO,
											   TrafficFortuneService trafficFortuneService){
		return runner ->{
//			demoTheBeforeAdvice(accountDAO, membershipDAO);

//			demoTheAfterAdvice(accountDAO);

//			demotheAfterThrowingAdvice(accountDAO);

//			demoTheAfterAdvice(accountDAO);

//			demoTheAroundAdvice(trafficFortuneService);

//			demoTheAroundAdviceHandleException(trafficFortuneService);

			demoTheAroundAdviceRethrowException(trafficFortuneService);
		};
	}

	private void demoTheAroundAdviceRethrowException(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdviceRethrowException");

		System.out.println("Calling getFortune()");

		boolean tripWire=true;
		String data=trafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: "+ data);

		System.out.println("Finished");
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdviceHandleException");

		System.out.println("Calling getFortune()");

		boolean tripWire=true;
		String data=trafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: "+ data);

		System.out.println("Finished");
	}

	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demoTheAdvice");

		System.out.println("Calling getFortune()");

		String data=trafficFortuneService.getFortune();

		System.out.println("\nMy fortune is: "+ data);

		System.out.println("Finished");
	}

	private void demoTheAfterAdvice(AccountDAO accountDAO) {
		List<Account> theAccounts = null;

		try{
			boolean tripWire=false;
			theAccounts=accountDAO.findAccounts(tripWire);


		}
		catch (Exception exc){
			System.out.println("\n\nMain Program: ... caught exception: " + exc);

		}

		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("----");

		System.out.println(theAccounts);
		System.out.println("\n");


	}

	private void demotheAfterThrowingAdvice(AccountDAO accountDAO) {

		List<Account> theAccounts = null;

		try{
			boolean tripWire=true;
			theAccounts=accountDAO.findAccounts(tripWire);


		}
		catch (Exception exc){
			System.out.println("\n\nMain Program: ... caught exception: " + exc);

		}

		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("----");

		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO accountDAO) {
		List<Account> theAccounts = accountDAO.findAccounts();

		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("----");

		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO,
									 MembershipDAO membershipDAO) {
		Account account=new Account();
		boolean isValue=true;
		accountDAO.addAccount(account, isValue);
//		accountDAO.doWork();
//
//		accountDAO.setName("jayp");
//		accountDAO.setServiceCode("silver");
//		accountDAO.getName();
//		accountDAO.getServiceCode();
//
//		membershipDAO.addSillyMember();
//		membershipDAO.goToSleep();

	}
}
