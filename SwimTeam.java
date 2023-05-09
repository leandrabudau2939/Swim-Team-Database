package main;
import java.io.File;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SwimTeam {
	static String firstName, lastName, stroke, distance, poolType, qualification, month, meet;
	
	public static void welcomeMessage() {
		System.out.println("Welcome to the Breakers Swim Team Database");
		System.out.println("1 - To check a swimmers best times");
		System.out.println("2 - To check a swimmers emergency contact information");
		System.out.println("3 - To check how much a swimmer has improved in an event");
		System.out.println("4 - To check coaches of a specific level");
		System.out.println("5 - To check which swim meets a swimmer qualifies for in an event");
		System.out.println("6 - To check how much the team has fundraised in a specific month");
		System.out.println("7 - To check which coaches have not finished meet official training");
		System.out.println("8 - To check how much time a swimmer needs to drop in an event to qualify for a meet");
		System.out.println("9 - To check which coaches haven't submitted reimbursment forms yet");
		System.out.println("10 - To check how much pool time each group has");
	}
	
	public static void repeat() {
		System.out.println("Would you like to do perform another query?");
		System.out.println("1 - Yes, I want to perform another query");
		System.out.println("2 - No, end the program");
	}
	
	public static void printStrokes() {
		System.out.println("1 - Freestyle");
		System.out.println("2 - Backstroke");
		System.out.println("3 - Breaststroke");
		System.out.println("4 - Butterfly");
		System.out.println("5 - IM");
	}
	
	public static void printRegularDistances() {
		System.out.println("1 - 50m");
		System.out.println("2 - 100m");
		System.out.println("3 - 200m");
	}
	
	public static void printFreestyleDistances() {
		System.out.println("1 - 50m");
		System.out.println("2 - 100m");
		System.out.println("3 - 200m");
		System.out.println("4 - 400m");
		System.out.println("5 - 800m");
		System.out.println("6 - 1500m");
	}
	
	public static void printPoolTypes() {
		System.out.println("1 - Short Course");
		System.out.println("2 - Long Course");
	}
	
	public static void printMonths() {
		System.out.println("1 - January			7 - July");
		System.out.println("2 - February			8 - August");
		System.out.println("3 - March			9 - September");
		System.out.println("4 - April			10 - October");
		System.out.println("5 - May				11 - November");
		System.out.println("6 - June			12 - December");
	}
	
	public static void main(String[]args) {
		ArrayList<String> result = new ArrayList<String>();
		boolean repeatLoop = true;
		while (repeatLoop) {
			welcomeMessage();
			Scanner response = new Scanner(System.in); 
			System.out.println("Enter choice: ");
			String choice = response.nextLine();
			if (choice.equals("1")) {
				System.out.println("Check swimmers best times");
				System.out.println("Please enter the swimmers first name: ");
				firstName = response.nextLine();
				System.out.println("Please enter the swimmers last name: ");
				lastName = response.nextLine();
				System.out.println("Please select the stroke: ");
				printStrokes();
				stroke = response.nextLine();
				System.out.print(stroke);
				switch(Integer.valueOf(stroke)) {
				case(1):
					stroke = "Freestyle";
					break;
				case(2):
					stroke = "Backstroke";
					break;
				case(3):
					stroke = "Breaststroke";
					break;
				case(4):
					stroke = "Butterfly";
					break;
				case(5):
					stroke = "IM";
				}
				System.out.println("Please select the distance: ");
				if (stroke == "1") {
					printFreestyleDistances();
				}
				else {
					printRegularDistances();
				}
				distance = response.nextLine();
				switch(distance) {
				case("1"):
					distance = "50";
					break;
				case("2"):
					distance = "100";
					break;
				case("3"):
					distance = "200";
					break;
				case("4"):
					distance = "400";
					break;
				case("5"):
					distance = "800";
					break;
				case("6"):
					distance = "1500";
				}
				System.out.println("Please select the pool length: ");
				printPoolTypes();
				poolType = response.nextLine();
				switch(poolType) {
				case("1"):
					poolType = "SCM";
					break;
				case("2"):
					poolType = "LCM";
				}
				result = BasicQueries.best_time(firstName, lastName, stroke, distance, poolType);
				System.out.println("");
				System.out.println(firstName + " " + lastName + "'s best time in the " + distance + "m " + stroke + " " + poolType + " is: " + result);
			}
			else if (choice.equals("2")) {
				System.out.println("Check swimmers emergency contact information");
				System.out.println("Please enter the swimmers first name:");
				firstName = response.nextLine();
				System.out.println("Please enter the swimmers last name:");
				lastName = response.nextLine();
				System.out.println("Swimmers Name: " + firstName + " " + lastName);
				result = BasicQueries.emergency_contact(firstName, lastName);
				System.out.println("");
				System.out.println("The emergency contact of " + firstName + " " + lastName + " is " + result);
			}
			else if (choice.equals("3")) {
				System.out.println("Check swimmers best times");
				System.out.println("Please enter the swimmers first name: ");
				firstName = response.nextLine();
				System.out.println("Please enter the swimmers last name: ");
				lastName = response.nextLine();
				System.out.println("Please select the stroke: ");
				printStrokes();
				stroke = response.nextLine();
				System.out.print(stroke);
				switch(Integer.valueOf(stroke)) {
				case(1):
					stroke = "Freestyle";
					break;
				case(2):
					stroke = "Backstroke";
					break;
				case(3):
					stroke = "Breaststroke";
					break;
				case(4):
					stroke = "Butterfly";
					break;
				case(5):
					stroke = "IM";
				}
				System.out.println("Please select the distance: ");
				if (stroke == "1") {
					printFreestyleDistances();
				}
				else {
					printRegularDistances();
				}
				distance = response.nextLine();
				switch(distance) {
				case("1"):
					distance = "50";
					break;
				case("2"):
					distance = "100";
					break;
				case("3"):
					distance = "200";
					break;
				case("4"):
					distance = "400";
					break;
				case("5"):
					distance = "800";
					break;
				case("6"):
					distance = "1500";
				}
				System.out.println("Please select the pool length: ");
				printPoolTypes();
				poolType = response.nextLine();
				switch(poolType) {
				case("1"):
					poolType = "SCM";
					break;
				case("2"):
					poolType = "LCM";
				}
				result = BasicQueries.time_improvement(firstName, lastName, stroke, distance, poolType);
				System.out.println("");
				System.out.println(firstName + " " + lastName + "'s worst time in the " + distance + "m " + stroke + " " + poolType + " is: " + result.get(0));
				System.out.println(firstName + " " + lastName + "'s best time in the " + distance + "m " + stroke + " " + poolType + " is: " + result.get(1));
				System.out.println("Therefore they have made an improvement of: " + result.get(2) + "seconds");
			}
			else if (choice.equals("4")) {
				System.out.println("Please enter the name of the coaching qualification you are searching for:");
				qualification = response.nextLine();
				result = BasicQueries.coaches_qualification(qualification);
				System.out.println("");
				System.out.println("The following coaches have " + qualification + " qualifications: " + result);
			}
			else if (choice.equals("5")) {
				System.out.println("Please enter the swimmers first name: ");
				firstName = response.nextLine();
				System.out.println("Please enter the swimmers last name: ");
				lastName = response.nextLine();
				System.out.println("Please select the stroke: ");
				printStrokes();
				stroke = response.nextLine();
				System.out.print(stroke);
				switch(Integer.valueOf(stroke)) {
				case(1):
					stroke = "Freestyle";
					break;
				case(2):
					stroke = "Backstroke";
					break;
				case(3):
					stroke = "Breaststroke";
					break;
				case(4):
					stroke = "Butterfly";
					break;
				case(5):
					stroke = "IM";
				}
				System.out.println("Please select the distance: ");
				if (stroke == "1") {
					printFreestyleDistances();
				}
				else {
					printRegularDistances();
				}
				distance = response.nextLine();
				switch(distance) {
				case("1"):
					distance = "50";
					break;
				case("2"):
					distance = "100";
					break;
				case("3"):
					distance = "200";
					break;
				case("4"):
					distance = "400";
					break;
				case("5"):
					distance = "800";
					break;
				case("6"):
					distance = "1500";
				}
				System.out.println("Please select the pool length: ");
				printPoolTypes();
				poolType = response.nextLine();
				switch(poolType) {
				case("1"):
					poolType = "SCM";
					break;
				case("2"):
					poolType = "LCM";
				}
				result = BasicQueries.meet_qualifications(firstName, lastName, stroke, distance, poolType);
				System.out.println("");
				System.out.println("In the " + distance + "m " + stroke + " " + poolType + ", " + firstName + " " + lastName + " qualifies for the following meets: " + result);
			}
			else if (choice.equals("6")) {
				System.out.println("Please enter the month you want to check :");
				printMonths();
				month = response.nextLine();
				result = BasicQueries.fundraising(month);
				System.out.println("");
				System.out.println("The team fundraised $" + result + " in that month");
			}
			else if (choice.equals("7")) {
				result = BasicQueries.volunteer_cred();
				System.out.println("");
				System.out.println("The coachs who have not finished the meet official training are: " + result);
			}
			else if (choice.equals("8")) {
				System.out.println("Please enter the swimmers first name: ");
				firstName = response.nextLine();
				System.out.println("Please enter the swimmers last name: ");
				lastName = response.nextLine();
				System.out.println("Please select the stroke: ");
				printStrokes();
				stroke = response.nextLine();
				System.out.print(stroke);
				switch(Integer.valueOf(stroke)) {
				case(1):
					stroke = "Freestyle";
					break;
				case(2):
					stroke = "Backstroke";
					break;
				case(3):
					stroke = "Breaststroke";
					break;
				case(4):
					stroke = "Butterfly";
					break;
				case(5):
					stroke = "IM";
				}
				System.out.println("Please select the distance: ");
				if (stroke == "1") {
					printFreestyleDistances();
				}
				else {
					printRegularDistances();
				}
				distance = response.nextLine();
				switch(distance) {
				case("1"):
					distance = "50";
					break;
				case("2"):
					distance = "100";
					break;
				case("3"):
					distance = "200";
					break;
				case("4"):
					distance = "400";
					break;
				case("5"):
					distance = "800";
					break;
				case("6"):
					distance = "1500";
				}
				System.out.println("Please select the pool length: ");
				printPoolTypes();
				poolType = response.nextLine();
				switch(poolType) {
				case("1"):
					poolType = "SCM";
					break;
				case("2"):
					poolType = "LCM";
				}
				System.out.println("Please enter the name of the swim meet you want to check: ");
				meet = response.nextLine();
				result = BasicQueries.time_to_drop(firstName, lastName, stroke, distance, poolType, meet);
				System.out.println("");
				System.out.println(firstName + " " + lastName + " needs to drop " + result + "s in their " + distance + "m " + stroke + " to qualify for " + meet);
			}
			else if (choice.equals("9")) {
				result = BasicQueries.reimbursement();
				System.out.println("");
				System.out.println("The people who have not submitted reimbursment forms are: " + result);
			}
			else if (choice.equals("10")) {
				result = BasicQueries.group_hours();
				System.out.println("");
				System.out.println("The groups and their pool time are as follows: " + result);
			}
			System.out.println("");
			repeat();
			choice = response.nextLine();
			if (choice.equals("2")) {
				repeatLoop = false;
			}
		}
	}
}
