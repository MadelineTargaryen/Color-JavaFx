import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/madeline/view/ColorView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("Color App");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
