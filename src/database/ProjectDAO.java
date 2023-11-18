package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import entities.Project;

public class ProjectDAO implements GenericDAO<Project> {
	private DatabaseConnector db = DatabaseConnector.getInstance();
	@Override
	public int insert(Project proj) {
		String sql = "INSERT INTO projects(name,date,description) VALUES(?,?,?)";
		int generatedId = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, proj.getName());
			pstmt.setString(2, proj.getDate().toString());
			pstmt.setString(3, proj.getDescription());
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
	public void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS projects (\n"
				+ " id integer PRIMARY KEY,\n"
				+ " name text NOT NULL,\n"
				+ " date text,\n"   
				+ " description text\n"
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
	public ArrayList<Project> getAll() {
		String sql = "Select * FROM projects";
		ArrayList<Project> projects = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int projectId = rs.getInt("id");
				String name = rs.getString("name");
				LocalDate date = LocalDate.parse(rs.getString("date"));
				String description = rs.getString("description");

				Project project = new Project(projectId, name, date, description);
				projects.add(project);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return projects;
	}

	@Override
	public void update(Project proj) {
		String sql = "UPDATE projects SET name = ?, date = ?, description = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, proj.getName());
			pstmt.setString(2, proj.getDate().toString());
			pstmt.setString(3, proj.getDescription());
			pstmt.setInt(4, proj.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	/* Delete project from database. Once deleted, tickets pertaining to the specific project are also deleted.
	 * Comments of ticket are also deleted.
	 * 
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	    String projectFind = "SELECT name FROM projects WHERE id = ?";
	    String deleteComments = "DELETE FROM comments WHERE ticketId IN (SELECT ticketId FROM tickets WHERE projectName = ?)";
	    String deleteTickets = "DELETE FROM tickets WHERE projectName = ?";
	    String deleteProject = "DELETE FROM projects WHERE id = ?";
	    
	    Connection conn = null;
	    PreparedStatement pstmtProjectFind = null;
	    PreparedStatement pstmtDeleteComments = null;
	    PreparedStatement pstmtDeleteTickets = null;
	    PreparedStatement pstmtDeleteProject = null;
	    ResultSet rs = null;
	    try {
	        conn = db.getConnection();
	        conn.setAutoCommit(false); 

	        pstmtProjectFind = conn.prepareStatement(projectFind);
	        pstmtProjectFind.setInt(1, id);
	        rs = pstmtProjectFind.executeQuery();

	        if (rs.next()) { 
	            String projectName = rs.getString("name");

	            pstmtDeleteComments = conn.prepareStatement(deleteComments);
	            pstmtDeleteComments.setString(1, projectName);
	            pstmtDeleteComments.executeUpdate();

	            pstmtDeleteTickets = conn.prepareStatement(deleteTickets);
	            pstmtDeleteTickets.setString(1, projectName);
	            pstmtDeleteTickets.executeUpdate();

	            pstmtDeleteProject = conn.prepareStatement(deleteProject);
	            pstmtDeleteProject.setInt(1, id);
	            pstmtDeleteProject.executeUpdate();

	            conn.commit();
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
}
