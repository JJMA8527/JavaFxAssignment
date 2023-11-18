package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entities.Comment;
import entities.Ticket;

public class CommentDAO implements GenericDAO<Comment> {
	private DatabaseConnector db = DatabaseConnector.getInstance();
	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE IF NOT EXISTS comments (\n"
				+ " commentId integer PRIMARY KEY,\n"
				+ " description text, \n"
				+ " date text, \n"
				+ " ticketId integer NOT NULL,\n" 
				+ " FOREIGN KEY(ticketId) REFERENCES tickets(id)\n"
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
	public int insert(Comment comment) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO comments(description,date,ticketId) VALUES(?,?,?)";
		int generatedId = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getDescription());
			pstmt.setString(2, comment.getTimestamp().toString());
			pstmt.setInt(3, comment.getTicketId());
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
	public ArrayList<Comment> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM comments";
		ArrayList<Comment> comments = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int commentId = rs.getInt("commentId");
				int ticketId = rs.getInt("ticketId");
				LocalDateTime date = LocalDateTime.parse(rs.getString("date"));
				String description = rs.getString("description");
				Comment comment = new Comment(commentId, ticketId,description,date);
				comments.add(comment);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return comments;
	}

	public ArrayList<Comment> getAll(int ticketId) { 
		String sql = "SELECT * FROM comments WHERE ticketId = ?";
		ArrayList<Comment> comments = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int commentId = rs.getInt("commentId");
				ticketId = rs.getInt("ticketId");
				LocalDateTime date = LocalDateTime.parse(rs.getString("date"));
				String description = rs.getString("description");
				Comment comment = new Comment(commentId, ticketId, description, date);
				comments.add(comment);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return comments;
	}

	@Override
	public void update(Comment comment) {
		// TODO Auto-generated method stub
		String sql = "UPDATE comments SET description = ?, date = ?, ticketId = ? WHERE commentId = ?";
		try {
			Connection conn = db.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql); 

			pstmt.setString(1, comment.getDescription());
			pstmt.setString(2, comment.getTimestamp().toString());
			pstmt.setInt(3, comment.getTicketId());
			pstmt.setInt(4, comment.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void delete(int commentId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM comments WHERE commentId = ?";
		try {
			Connection conn = db.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, commentId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
