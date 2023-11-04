/* Manage database interactions for the application. Establishes connection to database. 
 * 
 */
package database;
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

import entities.Project;
import entities.Ticket;

public class DatabaseConnector {
	// Establishes connection to database and returns connection object
    private static DatabaseConnector instance;
    private Connection conn;

	 private DatabaseConnector() {
	     try {
	         conn = DriverManager.getConnection("jdbc:sqlite:bugtracker.db");
	     } catch (SQLException e) {
		        System.out.println(e.getMessage());
	     }
	 }
	 
	 public static synchronized DatabaseConnector getInstance() {
	     if (instance == null) {
	         instance = new DatabaseConnector();
	      }
	      return instance;
	 }

	 public Connection getConnection() {
	      return conn;
	 }

}
