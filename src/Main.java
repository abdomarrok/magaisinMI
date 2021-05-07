


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {

    public Stage stage;
    private Scene scene;
     @FXML
    TextField userId;
    @FXML
    PasswordField password;
  
   
    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;

    
   public static void main(String[] args) {
        launch(args);
    }

 
     @Override
    public void start(Stage stage) throws Exception {
           Parent root;
         
        root = FXMLLoader.load(getClass().getResource("/mymagasin/fxml_files/login.fxml"));
            scene = new Scene(root);
            stage.setTitle("magsin MI");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
       
    }
 
}

