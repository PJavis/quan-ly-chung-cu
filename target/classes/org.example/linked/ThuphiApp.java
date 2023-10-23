import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ThuphiApp extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Thuphi.fxml"));
            Pane root = loader.load();

            // Apply the CSS file with the same name as the FXML file
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("Thuphi.css").toExternalForm());

            stage.setScene(scene);
            stage.setTitle("Thuphi App");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
