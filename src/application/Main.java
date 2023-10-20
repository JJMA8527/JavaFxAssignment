package application;
	
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
		launch(args);
	}
}
