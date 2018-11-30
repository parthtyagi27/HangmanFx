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
public class ArrayUtil
{
    private char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private byte[] state = new byte[26];
    
    public ArrayUtil()
    {
        for(short i = 0; i < state.length; i++)
            state[i] = 0;
    }
    
    public void add(char c)
    {
        for(short i = 0; i < alphabet.length; i++)
        {
            if(alphabet[i] == c)
            {
                state[i] = 1;
                break;
            }
        }
    }
    
    public boolean inArray(char c)
    {
        for(short i = 0; i < alphabet.length; i++)
        {
            if(alphabet[i] == c)
            {
               if(state[i] == 1)
                   return true;
               else
                   return false;
            }
        }
        return false;
    }
}
