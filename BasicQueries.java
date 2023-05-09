package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BasicQueries {
	
	public static ArrayList<String> emergency_contact(String fname, String lname) {
		Statement stmt = null; 
		Connection c = null; 
		ArrayList<String> s = new ArrayList<String>();
		try {
			String url = "jdbc:sqlite:C:\\sqlite3\\SwimTeamDatabase.db";
			c = DriverManager.getConnection(url);
			System.out.println("Connection to SQlite has been established");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			stmt = c.createStatement();
			String sql = "SELECT emergency_contact.Name FROM emergency_contact \r\n"
					+ "LEFT JOIN SwimmerEmergency_Contact on (emergency_contact.Name = SwimmerEmergency_Contact.ContactName AND emergency_contact.PhoneNumber = SwimmerEmergency_Contact.ContactPhoneNumber)\r\n"
					+ "LEFT JOIN swimmer on (swimmer.SwimmerID = SwimmerEmergency_Contact.SwimmerID)\r\n"
					+ "LEFT JOIN person on (swimmer.SwimmerID = person.ID)\r\n"
					+ "WHERE person.First_Name = ";
			sql = sql + "'" + fname + "'" + " AND person.Last_Name = '" + lname + "';";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				s.add(rs.getString(1));
			}
			stmt.close();
		}catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return s;
		
	}
	
	public static ArrayList<String> best_time(String fname, String lname, String stroke, String distance, String poolType) {
		Statement stmt = null; 
		Connection c = null; 
		ArrayList<String> s = new ArrayList<String>();
		try {
			String url = "jdbc:sqlite:C:\\sqlite3\\SwimTeamDatabase.db";
			c = DriverManager.getConnection(url);
			System.out.println("Connection to SQlite has been established");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			stmt = c.createStatement();
			String sql = "SELECT MIN(Time) FROM PersonEvent\r\n"
					+ "LEFT JOIN person ON PersonEvent.PersonID = person.ID\r\n"
					+ "WHERE PersonEvent.EventDistance = ";
			sql = sql + Integer.valueOf(distance) + " AND PersonEvent.EventStroke = '" + stroke + "' AND PersonEvent.EventCourseType = '" + poolType + "' AND ";
			sql = sql + "person.First_Name = '" + fname + "' AND person.Last_Name = '" + lname + "';";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				s.add(rs.getString(1));
			}
			stmt.close();
		}catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return s;
		
	}
	
	public static ArrayList<String> time_improvement(String fname, String lname, String stroke, String distance, String poolType) {
		Statement stmt = null; 
		Connection c = null; 
		ArrayList<String> s = new ArrayList<String>();
		try {
			String url = "jdbc:sqlite:C:\\sqlite3\\SwimTeamDatabase.db";
			c = DriverManager.getConnection(url);
			System.out.println("Connection to SQlite has been established");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			stmt = c.createStatement();
			String sql = "SELECT  MAX(Time) as max_time, MIN(Time) as min_time, (MAX(PersonEvent.time) - MIN(PersonEvent.time)) as time_improvement FROM PersonEvent\r\n"
					+ "LEFT JOIN person ON PersonEvent.PersonID = person.ID\r\n"
					+ "WHERE PersonEvent.EventDistance = ";
			sql = sql + Integer.valueOf(distance) + " AND PersonEvent.EventStroke = '" + stroke + "' AND PersonEvent.EventCourseType = '" + poolType + "' AND ";
			sql = sql + "person.First_Name = '" + fname + "' AND person.Last_Name = '" + lname + "';";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				s.add(String.valueOf(rs.getString("max_time")));
				s.add(String.valueOf(rs.getString("min_time")));
				s.add(String.valueOf(rs.getString("time_improvement")));
			}
			stmt.close();
		}catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return s;
		
	}
	
	public static ArrayList<String> coaches_qualification(String qualification) {
		Statement stmt = null; 
		Connection c = null; 
		ArrayList<String> s = new ArrayList<String>();
		try {
			String url = "jdbc:sqlite:C:\\sqlite3\\SwimTeamDatabase.db";
			c = DriverManager.getConnection(url);
			System.out.println("Connection to SQlite has been established");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			stmt = c.createStatement();
			String sql = "SELECT person.First_Name as fn, person.Last_Name as ln FROM CoachQualification\r\n"
					+ "LEFT JOIN coach ON CoachQualification.SwimCanada_Number = coach.SwimCanada_Number\r\n"
					+ "LEFT JOIN person ON coach.CoachID = person.ID\r\n"
					+ "WHERE CoachQualification.QualificationTitle = ";
			sql = sql + "'" + qualification + "';";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name;
				name = String.valueOf(rs.getString("fn")) + " " + String.valueOf(rs.getString("ln"));
				s.add(name);
			}
			stmt.close();
		}catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return s;
		
	}
	
	public static ArrayList<String> meet_qualifications(String fname, String lname, String stroke, String distance, String poolType) {
		Statement stmt = null; 
		Connection c = null; 
		ArrayList<String> s = new ArrayList<String>();
		try {
			String url = "jdbc:sqlite:C:\\sqlite3\\SwimTeamDatabase.db";
			c = DriverManager.getConnection(url);
			System.out.println("Connection to SQlite has been established");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			stmt = c.createStatement();
			String sql = "SELECT event.MeetTitle as title, event.MeetYear as year FROM PersonEvent\r\n"
					+ "LEFT JOIN person ON PersonEvent.PersonID = person.ID\r\n"
					+ "CROSS JOIN event\r\n"
					+ "WHERE (PersonEvent.EventDistance = event.Distance AND event.Distance = ";
			sql = sql + distance + ") AND (PersonEvent.EventStroke = event.Stroke AND event.Stroke = '" + stroke + "') AND PersonEvent.Time < event.QualifyingTime AND person.First_Name = '" + fname + "';";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name;
				name = String.valueOf(rs.getString("title")) + " " + String.valueOf(rs.getString("year"));
				s.add(name);
			}
			stmt.close();
		}catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return s;
		
	}
	
	public static ArrayList<String> fundraising(String month) {
		Statement stmt = null; 
		Connection c = null; 
		ArrayList<String> s = new ArrayList<String>();
		try {
			String url = "jdbc:sqlite:C:\\sqlite3\\SwimTeamDatabase.db";
			c = DriverManager.getConnection(url);
			System.out.println("Connection to SQlite has been established");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			stmt = c.createStatement();
			String sql = "SELECT SUM(COST) as fund_total FROM financial_charge\r\n"
					+ "WHERE strftime('%m', Date_Charged) = ";
			sql = sql + "'" + month + "'" + " AND Reason LIKE 'Fundraising';";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				s.add(String.valueOf(rs.getString("fund_total")));
			}
			stmt.close();
		}catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return s;
		
	}
	
	public static ArrayList<String> volunteer_cred() {
		Statement stmt = null; 
		Connection c = null; 
		ArrayList<String> s = new ArrayList<String>();
		try {
			String url = "jdbc:sqlite:C:\\sqlite3\\SwimTeamDatabase.db";
			c = DriverManager.getConnection(url);
			System.out.println("Connection to SQlite has been established");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			stmt = c.createStatement();
			String sql = "SELECT person.First_Name as fname, person.Last_Name as lname FROM CoachQualification\r\n"
					+ "LEFT JOIN coach ON CoachQualification.SwimCanada_Number = coach.SwimCanada_Number\r\n"
					+ "LEFT JOIN person ON coach.CoachID = person.ID\r\n"
					+ "WHERE CoachQualification.QualificationTitle = 'Meet Official Training' AND CoachQualification.Progress = 'Evaluation Pending';";
			ResultSet rs = stmt.executeQuery(sql);
			String name;
			while (rs.next()) {
				name = String.valueOf(rs.getString("fname")) + " " + String.valueOf(rs.getString("lname"));
				s.add(name);
			}
			stmt.close();
		}catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return s;
		
	}
	
	public static ArrayList<String> time_to_drop(String fname, String lname, String stroke, String distance, String poolType, String meet) {
		Statement stmt = null; 
		Connection c = null; 
		ArrayList<String> s = new ArrayList<String>();
		try {
			String url = "jdbc:sqlite:C:\\sqlite3\\SwimTeamDatabase.db";
			c = DriverManager.getConnection(url);
			System.out.println("Connection to SQlite has been established");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			stmt = c.createStatement();
			String sql = "SELECT person.First_Name, person.Last_Name, event.Distance, event.Stroke, (PersonEvent.Time-event.QualifyingTime) as time FROM PersonEvent\r\n"
					+ "LEFT JOIN person ON PersonEvent.PersonID = person.ID\r\n"
					+ "CROSS JOIN event\r\n"
					+ "WHERE (PersonEvent.EventDistance = event.Distance AND event.Distance = ";
			sql = sql + distance + ") AND (PersonEvent.EventStroke = event.Stroke AND event.stroke = '" + stroke + "') AND (event.MeetTitle = '" + meet + "') AND person.First_Name = '" + fname + "';";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				s.add(String.valueOf(rs.getString("time")));
			}
			stmt.close();
		}catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return s;
		
	}
	
	public static ArrayList<String> reimbursement() {
		Statement stmt = null; 
		Connection c = null; 
		ArrayList<String> s = new ArrayList<String>();
		try {
			String url = "jdbc:sqlite:C:\\sqlite3\\SwimTeamDatabase.db";
			c = DriverManager.getConnection(url);
			System.out.println("Connection to SQlite has been established");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			stmt = c.createStatement();
			String sql = "SELECT person.First_Name as fname, person.Last_Name as lname FROM financial_charge\r\n"
					+ "LEFT JOIN person ON financial_charge.PersonID = person.ID\r\n"
					+ "WHERE financial_charge.status = 'Pending Reimbursement Form Submission';";
			ResultSet rs = stmt.executeQuery(sql);
			String name;
			while (rs.next()) {
				name = String.valueOf(rs.getString("fname")) + " " + String.valueOf(rs.getString("lname"));
				s.add(name);
			}
			stmt.close();
		}catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return s;
		
	}
	
	public static ArrayList<String> group_hours() {
		Statement stmt = null; 
		Connection c = null; 
		ArrayList<String> s = new ArrayList<String>();
		try {
			String url = "jdbc:sqlite:C:\\sqlite3\\SwimTeamDatabase.db";
			c = DriverManager.getConnection(url);
			System.out.println("Connection to SQlite has been established");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			stmt = c.createStatement();
			String sql = "SELECT GroupTitle as gname, SUM(Hours) as hours FROM practice\r\n"
					+ "WHERE Type = 'Pool';";
			ResultSet rs = stmt.executeQuery(sql);
			String name;
			while (rs.next()) {
				name = String.valueOf(rs.getString("gname")) + " = " + String.valueOf(rs.getString("hours"));
				s.add(name);
			}
			stmt.close();
		}catch (Exception e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return s;
		
	}

}
