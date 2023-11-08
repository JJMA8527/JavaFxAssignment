package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import entities.Ticket;

public class TicketDAO implements GenericDAO<Ticket> {
	private DatabaseConnector db = DatabaseConnector.getInstance();

	@Override
	public void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS tickets (\n"
				+ " ticketId integer PRIMARY KEY,\n"
				+ " name text NOT NULL,\n"
				+ " projectName text NOT NULL,\n" 
				+ " date text, \n"
				+ " description text, \n"
				+ " ticketType text \n"
				+ ");";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int insert(Ticket ticket) {
		String sql = "INSERT INTO tickets(name,projectName,date,description, ticketType) VALUES(?,?,?,?,?)";
		int generatedId = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ticket.getName());
			pstmt.setString(2, ticket.getProjectName());
			pstmt.setString(3, ticket.getDate().toString());
			pstmt.setString(4, ticket.getDescription());
			pstmt.setString(5, ticket.getTicketType());
			pstmt.executeUpdate();

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT last_insert_rowid()");
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return generatedId;
	}

	@Override
	public ArrayList<Ticket> getAll() {
		String sql = "SELECT * FROM tickets";
		ArrayList<Ticket> tickets = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int ticketId = rs.getInt("ticketId");
				//int projectId = rs.getInt("projectId");
				String name = rs.getString("name");
				String projectName = rs.getString("projectName");
				LocalDate date = LocalDate.parse(rs.getString("date"));
				String description = rs.getString("description");
				String type = rs.getString("ticketType");
				Ticket ticket = new Ticket(ticketId,name,projectName, date, description, type);
				tickets.add(ticket);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return tickets;
	}
	@Override
	public void update(Ticket tic) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
