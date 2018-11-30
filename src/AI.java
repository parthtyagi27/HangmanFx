/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanfx;

/**
 *
 * @author Parth
 */
class AI
{

    private String[] words;
    private char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'}; 
    private char[] possibleLetters = alphabet;
    private float[] probability;
    private boolean wrongLetters = false;
    private String givenWord = "";
    public static Index[] indices;
    private boolean checkLength = false;
    
    public AI(String[] list)
    {
        words = list;
        probability = new float[words.length];
    }

    public void playTurn()
    {
        refinePossibleLetters();
        System.out.println("Possible Letters: ");
        for(char c : possibleLetters)
            System.out.println(c);
        refineList();
    }

    public void setWord(String word)
    {
        givenWord = "";
        for(short i = 0; i < word.length(); i++)
        {
            if(word.charAt(i) != ' ')
                givenWord += word.charAt(i);
        }
        System.out.println("AI: given word = " + givenWord);
    }
    private void refineList()
    {
        if(checkLength == false)
        {
            removeLength();
            checkLength = true;
        }
        
        char letter = givenWord.charAt(0);
        if(letter != '_')
        {
            for(int i = 0; i < words.length; i++)
            {
                if(probability[i] == 0.0f)
                    continue;
                else
                {
                    if(words[i].charAt(0) != letter)
                        probability[i] = 0.0f;
                }
            }
            System.out.println("Refined List");
        }
        if(wrongLetters == true)
        {
            for(int i = 0; i < words.length; i++)
            {
                if(probability[i] == 0.0f)
                    continue;
                else
                {
                    if(words[i].charAt(0) != letter)
                        probability[i] = 0.0f;
                }
            }
            System.out.println("Refined List");
        }
        printPossibleWords();
    }
    
    public void removeLetter(char c)
    {
        for(short i = 0; i < alphabet.length; i++)
        {
            if(alphabet[i] == c)
            {
                alphabet[i] = ' ' ;
                break;
            }
        }
    }

    private short getPos(char charAt)
    {
        for(short i = 0; i < alphabet.length; i++)
            if(alphabet[i] == charAt)
            {
                System.out.println(i);
                return i;
            }
        return -1;
    }
    
    private void removeLength()
    {
        for(int i = 0; i < words.length; i++)
        {
            if(words[i].length() == givenWord.length())
            {
                probability[i] = 1f;
            }
        }        
    }

    private void printPossibleWords()
    {
        for(int i = 0; i < probability.length; i++)
        {
            if(probability[i] != 0.0f)
                System.out.println("Possible word: " + words[i] + " with a value of: " + probability[i]);
        }
    }

    private void refinePossibleLetters()
    {
        if(!AIFXMLController.wrongLetterList.isEmpty())
        {
            for(byte i = 0; i < AIFXMLController.wrongLetterList.size(); i++)
            {
                for(byte j = 0; j < possibleLetters.length; j++)
                {
                    if(AIFXMLController.wrongLetterList.get(i) == possibleLetters[j])
                        possibleLetters[j] = ' ';
                }
            }
            wrongLetters = true;
        }
    }
}
