
package hangmanfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parth
 */
public class ReadFile extends Thread
{

    private BufferedReader reader;
    private InputStream in;
    private String[] array = new String[25322]; 
    
    public ReadFile()
    {
        in = getClass().getResourceAsStream("words.txt");
        reader = new BufferedReader(new InputStreamReader(in));
    }
    
    @Override
    public void run()
    {
        try
        {
            System.out.println("Starting file reading process: ");
            char currentLetter = 'a';
            int counter = 0;
            for(int i = 0; i < array.length; i++)
            {
                try
                {
                    array[i] = reader.readLine();
                    char c = array[i].charAt(0);
                    if(c != currentLetter && counter != 0)
                    {
                        AI.indices[counter] = new Index(AI.indices[counter - 1].getEndIndex() + 1, i, currentLetter);
                        currentLetter = c;
                        System.out.println("Added another index for letter: " + AI.indices[counter].getLetter());
                        counter++;
                    }
                    else if(c != currentLetter && counter == 0)
                    {
                        AI.indices[counter] = new Index(0, i, currentLetter);
                        System.out.println("Added another index for letter: " + AI.indices[counter].getLetter());
                        currentLetter = c;
                        counter++;
                    }
                } catch (IOException ex)
                {
                    Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
            AI.indices[counter] = new Index(AI.indices[counter - 1].getEndIndex() + 1, array.length, currentLetter);
            System.out.println("Finished loading lines into array ... quitting thread");
            in.close();
            reader.close();
        } catch (IOException ex)
        {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String[] getLines()
    {
        return array;
    }
    
}
