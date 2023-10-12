package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class SQLController {
	
	private Connection connect() {
		Connection conn = null;
	    try {
	        conn = DriverManager.getConnection("jdbc:sqlite:projects.db");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return conn;
	}
	
	public void insertProject(Project proj) {
		//sql INSERT INTO statement. create a table projects. project info into columns
		String sql = "INSERT INTO projects(name,data,description) VALUES(?,?,?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
               pstmt.setString(1, project.getName());
               pstmt.setString(2, project.getDate().toString());
               pstmt.setString(3, project.getDescription());
               pstmt.executeUpdate();
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
	}
	
	public ArrayList<Project>getProjects(){
		//select all
		String sql = "Select * FROM projects";
		ArrayList<Project>projects = new ArrayList<>();
		
		try (Connection conn = this.connect();
			Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql)){
           
           while (rs.next()) {
        	   String name = rs.getString("name");
        	   LocalDate date = LocalDate.parse(rs.getString("date"));
        	   String description = rs.getString(description);
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
		
		return projects;
	}
	
	

    
}
