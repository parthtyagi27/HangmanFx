/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanfx;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Parth
 */
public class AIFXMLController implements Initializable
{

    private AI ai;
    
    private String[] array;
    private String word;
    private String encryptedWord = "";
    private Image[] images;
    private static int tries = 0;
    public static ArrayList<Character> wrongLetterList = new ArrayList<>();
    
    @FXML
    private Label stringTxt;
    @FXML
    private Button guessBtn;
    @FXML
    private Button newWordBtn;
    @FXML
    private Button guessWordBtn;
    @FXML
    private TextField inputTxtField;
    @FXML
    private TextField wordInputText;
    @FXML
    private TextArea wrongTxtArea;
    @FXML
    private ImageView imageView;
    @FXML
    private Button optionBtn;
    @FXML
    private TextArea aiTextArea;
    
    @FXML
    private void guessBtnAction(ActionEvent event) throws IOException, URISyntaxException
    {
        String input = inputTxtField.getText();
        char[] currentState = encryptedWord.toCharArray();
        String finalString = "";
        boolean rightGuess = false;

        if(input == "" || input.length() > 1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
        else if(wrongLetterList.contains(input.charAt(0)) == false) 
        {
            char letter = input.charAt(0);
            for(short i = 0; i < word.length(); i++)
            {
                if(word.charAt(i) == letter)
                {
                    currentState[i] = letter;  
                    rightGuess = true;
                }
            }
            for(short k = 0; k < currentState.length; k++)
                finalString += currentState[k];
            if(rightGuess != true)
            {
                wrongTxtArea.setText(wrongTxtArea.getText() + " " + letter);
                tries++;
                updateImage();
                wrongLetterList.add(letter);
            }else
            {
                encryptedWord = finalString;
                printLabel(finalString);
            }
            if(checkIfGuessed(finalString) == true)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("HangmanFx");
                alert.setHeaderText("Congratulations");
                alert.setContentText("You guessed the word! We're setting up another word for you now!");
                alert.showAndWait();
                resetGame();
            }
        }
        else if(wrongLetterList.contains(input.charAt(0)) == true)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You've already guessed this letter and its wrong :(");
            alert.showAndWait();
        }
        inputTxtField.setText("");
        ai.setWord(encryptedWord);
        ai.playTurn();
    }
    @FXML
    private void newBtnAction(ActionEvent event)
    {
        generateRandomWord();
        System.out.println("New word: " + word);
        setupWordDisplay();
        inputTxtField.setText("");
        wordInputText.setText("");
    }
    @FXML
    private void guessWordBtnAction(ActionEvent event)
    {
        String input = wordInputText.getText();
        if(input.equals(word))
        {
            printLabel(word);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("HangmanFx");
            alert.setHeaderText("Congratulations");
            alert.setContentText("You guessed the word! We're setting up another word for you now!");
            alert.showAndWait();
            resetGame();
        }else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("HangmanFx");
            alert.setHeaderText("Sorry");
            alert.setContentText("Incorrect Guess. Try again.");    
            alert.showAndWait();
            tries++;
            updateImage();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        images = new Image[8];
        images[0] = new Image(getClass().getResourceAsStream("base.png"));
        for(short i = 1; i <= 7; i++)
        {
            images[i] = new Image(getClass().getResourceAsStream(i + ".png"));
        }
        imageView.setImage(images[0]);
        
        array = HangmanFx.getList();
        generateRandomWord();
        System.out.println(word);
        setupWordDisplay();
        
        ai = new AI(array);
    }    
    
    private void generateRandomWord()
    {
        Random r = new Random();
        word = array[r.nextInt(array.length - 0) + 0];
    }

    private void setupWordDisplay()
    {
        encryptedWord = "";
        for(short i = 0; i < word.length(); i++)
        {
            encryptedWord += "_";
        }
        printLabel(encryptedWord);
    }
    
    private void printLabel(String text)
    {
        stringTxt.setText("");
        String f = "";
        for(short i = 0; i < text.length(); i++)
            f += text.charAt(i) + " ";
        stringTxt.setText(f);
    }
    private void updateImage()
    {
        if(tries >= 7)
        {
            imageView.setImage(images[7]);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("HangmanFx");
            alert.setHeaderText("Sorry");
            alert.setContentText("You lost :( The right word was: " + word + ". We're setting up another word for you now!");
            alert.showAndWait();
            resetGame();
        }
        imageView.setImage(images[tries]);
        
    }
    
    public void resetGame()
    {
        generateRandomWord();
        System.out.println("new Word is: "  + word);
        setupWordDisplay();
        inputTxtField.setText("");
        wordInputText.setText("");
        wrongTxtArea.clear();
        tries = 0;
        updateImage();
    }

    private boolean checkIfGuessed(String finalString)
    {
        String temp = "";
        for(short i = 0; i < finalString.length(); i++)
        {
            if(finalString.charAt(i) != ' ')
                temp += finalString.charAt(i);
        }
        return temp.equals(word) == true;
    }
    
    @FXML
    private void optionBtnAction(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("OptionsFXML.fxml"));
        Scene scene = new Scene(root);
        HangmanFx.window.setScene(scene);
    }
}
