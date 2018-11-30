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
public class Index
{
    private int startIndex = 0, endIndex = 0;
    private char c = ' ';
    
    public Index(int begin, int end, char letter)
    {
        startIndex = begin;
        endIndex = end;
        c = letter;
    }
    
    public int getStartIndex()
    {
        return startIndex;
    }
    
    public int getEndIndex()
    {
        return endIndex;
    }
    
    public char getLetter()
    {
        return c;
    }
}
