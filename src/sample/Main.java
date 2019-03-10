/*

This is a WIP and has multiple bugs and incomplete portions of code. Please see each class for an in-depth review of each issue and incomplete portions pertaining to each respective class.
Bugs/Issues/Incomplete:
1. Path finding not fully complete. Controller.java
2. Route printing not fully complete Controller.java
3. Shortest route finding attempted. Controller.java Path.java
4. Fastest route finding not fully implemented or complete. Controller.java
5. User linking for landmarks and roads not implemented. Controller.java
6. Scroll action for route textfield not complete. Controller.java

 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Mathew Peder
 * <p>
 * Applied Computing - 20073231
 */

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Navigation App");
        primaryStage.setScene(new Scene(root, 600, 400 ));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
