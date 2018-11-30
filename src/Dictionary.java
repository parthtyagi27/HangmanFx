/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parth
 */
public class Dictionary
{
    private InputStream in;
    private BufferedReader reader;
    public static List<String> list;
    public static String[] array = new String[25322];
    
    public Dictionary() throws IOException, URISyntaxException
    {
        list = new ArrayList<>();
        in = getClass().getResourceAsStream("words.txt"); 
        reader = new BufferedReader(new InputStreamReader(in));
        
        while(reader.readLine() != null)
        {
            list.add(reader.readLine());
        }
        
        Thread t = new Thread(() ->
        {
            for(int i = 0; i < array.length; i++)
            {
                try
                {
                    array[i] = reader.readLine();
                } catch (IOException ex)
                {
                    Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Added: " + array[i] + " to array at index: " + i);
            }
        });
        t.start();
        System.out.println("Loaded word list into array ... " + list.size() + " items ");
        in.close();
        reader.close();
        System.out.println("Closed IO...");
    }
    
    public String check() throws IOException
    {
       return list.get(list.size() - 1);
    }
    
    public ArrayList<String> getList()
    {
        return (ArrayList<String>) list;
    }
}
