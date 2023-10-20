/* Manage database interactions for the application. Establishes connection to database, creates a table, insert
 * entities into table, and defines a method to retrieve list of items. 
 * 
 */
package controller;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import application.Project;

public class SQLController {
	// Establishes connection to database and returns connection object
	private Connection connect() {
		Connection conn = null;
	    try {
	        conn = DriverManager.getConnection("jdbc:sqlite:bugtracker.db");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return conn;
	}
	// Inserts new Project object into the projects table in database
	public int insertProject(Project proj) {
		//sql INSERT INTO statement. create a table projects. project info into columns
		String sql = "INSERT INTO projects(name,date,description) VALUES(?,?,?)";
   	    int generatedId = 0;
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
               pstmt.setString(1, proj.getName());
               pstmt.setString(2, proj.getDate().toString());
               pstmt.setString(3, proj.getDescription());
               pstmt.executeUpdate();
               try (Statement stmt = conn.createStatement()) {
            	    ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
            	    if (rs.next()) {
            	        generatedId = rs.getInt(1);
            	    }
               }
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
        return generatedId;
	}
	//Creates projects table if it doesn't already exists. Checks for connection
	public void createProjectsTable() {
	    String sql = "CREATE TABLE IF NOT EXISTS projects (\n"
	            + " id integer PRIMARY KEY,\n"
	            + " name text NOT NULL,\n"
	            + " date text,\n"   
	            + " description text\n"
	            + ");";

	    try (Connection conn = this.connect();
	            Statement stmt = conn.createStatement()) {
	        stmt.execute(sql);
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	//Retrieves all projects from table in database
	public ArrayList<Project>getProjects(){
		//select all
		String sql = "Select * FROM projects";
		ArrayList<Project>projects = new ArrayList<>();
		
		try (Connection conn = this.connect();
			Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql)){
           
           while (rs.next()) {
        	   int projectId = rs.getInt("id");
        	   String name = rs.getString("name");
        	   LocalDate date = LocalDate.parse(rs.getString("date"));
        	   String description = rs.getString("description");
        	   
        	    Project project = new Project(projectId,name, date, description);
        	    projects.add(project);
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
		
		return projects;
	}
    
}
