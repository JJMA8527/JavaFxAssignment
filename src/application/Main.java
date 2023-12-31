package application;
	


import database.CommentDAO;
import database.ProjectDAO;
import database.TicketDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import layout.HomeLayout;
import javafx.scene.Scene;




public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

            HomeLayout home = new HomeLayout(primaryStage);
            Scene scene = home.getScene();

			primaryStage.setTitle("Home Page");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		initializeDatabase();
		launch(args);
	}

	private static void initializeDatabase() {
		// TODO Auto-generated method stub
        ProjectDAO projdb = new ProjectDAO();
        TicketDAO tickdb = new TicketDAO();
        CommentDAO commentdb = new CommentDAO();
        projdb.createTable();
        tickdb.createTable();
        commentdb.createTable();
	}
	
}
