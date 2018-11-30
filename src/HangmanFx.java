
package hangmanfx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Parth
 */
public class HangmanFx extends Application
{
    public static Stage window;
    private static ReadFile r;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        AI.indices = new Index[26];
        r = new ReadFile();
        
        r.start();
        Parent root = FXMLLoader.load(getClass().getResource("OptionsFXML.fxml"));
        r.join();
        Scene scene = new Scene(root);
        window = stage;
        window.setScene(scene);
        window.show();
        window.setResizable(false);
        window.setTitle("HangmanFx");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
    public void showOptions() throws IOException
    {
        Scene optionsScene = new Scene(FXMLLoader.load(getClass().getResource("MainFXML.fxml")));
    }
    
    public static String[] getList()
    {
        return r.getLines();
    }
}
