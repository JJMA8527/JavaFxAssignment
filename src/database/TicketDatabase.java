package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.SQLController;
import entities.Ticket;

public class TicketDatabase extends SQLController {
	
	public void createTicketsTable() {
	    String sql = "CREATE TABLE IF NOT EXISTS tickets (\n"
	        + " ticketId integer PRIMARY KEY,\n"
	        + " name text NOT NULL,\n"
	        + " date text, \n"
            + " description text, \n"
            + " projectId integer NOT NULL,\n" 
	        + " ticketType text, \n"
	        + " FOREIGN KEY(projectId) REFERENCES projects(id)\n"
	        + ");";

	    try (Connection conn = this.connect();
	         Statement stmt = conn.createStatement()) {
	        stmt.execute(sql);
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public int insertTicket(Ticket ticket) {
	    String sql = "INSERT INTO tickets(name,date,description,projectId, ticketType) VALUES(?,?,?,?,?)";
	    int generatedId = 0;

	    try (Connection conn = this.connect();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ticket.getName());
            pstmt.setString(2, ticket.getDate().toString());
            pstmt.setString(3, ticket.getDescription());
	        pstmt.setInt(4, ticket.getProjectId());
	        pstmt.setString(5, ticket.getTicketType());
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
	/* Method overloading. One method with a project id parameter used to
	 * get tickets from that specific project. Other method is retrieving all tickets. 
	 * 	
	 */
	public ArrayList<Ticket> getTickets(int projectId) { 
	    String sql = "SELECT * FROM tickets WHERE projectId = ?";
	    ArrayList<Ticket> tickets = new ArrayList<>();

	    try (Connection conn = this.connect();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, projectId);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int ticketId = rs.getInt("ticketId");
	            projectId = rs.getInt("projectId");
	        	String name = rs.getString("name");
	        	LocalDate date = LocalDate.parse(rs.getString("date"));
	        	String description = rs.getString("description");
	        	String type = rs.getString("ticketType");
	            Ticket ticket = new Ticket(ticketId, projectId, name,date,description,type);
	            tickets.add(ticket);
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }

	    return tickets;
	}
	public ArrayList<Ticket> getTickets() {
	    String sql = "SELECT * FROM tickets";
	    ArrayList<Ticket> tickets = new ArrayList<>();

	    try (Connection conn = this.connect();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int ticketId = rs.getInt("ticketId");
	            int projectId = rs.getInt("projectId");
	            String name = rs.getString("name");
	            LocalDate date = LocalDate.parse(rs.getString("date"));
	            String description = rs.getString("description");
	        	String type = rs.getString("ticketType");
	            Ticket ticket = new Ticket(ticketId, projectId, name, date, description,type);
	            tickets.add(ticket);
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }

	    return tickets;
	}
    
}
