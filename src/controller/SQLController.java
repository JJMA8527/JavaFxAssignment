/* Manage database interactions for the application. Establishes connection to database. 
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

import entities.Project;
import entities.Ticket;

public class SQLController {
	// Establishes connection to database and returns connection object
	protected Connection connect() {
		Connection conn = null;
	    try {
	        conn = DriverManager.getConnection("jdbc:sqlite:bugtracker.db");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return conn;
	}

}
