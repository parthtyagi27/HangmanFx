/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanfx;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

/**
 *
 * @author Parth
 */
public class OptionsController
{
    @FXML
    private RadioButton playNormal;
    @FXML
    private RadioButton playAI;
    @FXML
    private Button playBtn;
    
    @FXML
    public void playBtnAction(ActionEvent event) throws IOException
    {
        if(playNormal.isSelected() == true)
        {
            Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
            Scene scene = new Scene(root);
            HangmanFx.window.setScene(scene);
        }
        else if(playAI.isSelected() == true)
        {
            Parent root = FXMLLoader.load(getClass().getResource("AIFXML.fxml"));
            Scene scene = new Scene(root);
            HangmanFx.window.setScene(scene);
            HangmanFx.window.setTitle("HangmanFX - AI");
        }
    }
}
