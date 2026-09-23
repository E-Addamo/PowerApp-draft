package com.powerapp.ParserTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;

import com.powerapp.Parser;
import com.powerapp.Heir;

import com.powerapp.InvalidHeirException;
import com.powerapp.FileFormatException;
import java.io.FileNotFoundException;

public class HeirParserTest {

    private static final String dir = System.getProperty("user.dir") + "/src/test/java/com/powerapp/ParserTests/";

    @Test 
    public void oneHeir() throws Exception{
        Parser parser = new Parser();
        ArrayList<Heir> heirList = parser.parseHeirs(dir + "HeirParserTestCase1.txt");
        Heir heir = new Heir("Mario", "Rossi", "AAAAAAAAAAAAAAAA",
        "IBANIBANIBANIBANIBANIBANAAA", 0.5f);
        assertEquals(heir, heirList.get(0));
    }

    @Test
    public void threeHeirs() throws Exception{
        Parser parser = new Parser();
        ArrayList<Heir> heirList = parser.parseHeirs(dir + "HeirParserTestCase2.txt");

        Heir heir1 = new Heir("Mario", "Rossi", "AAAAAAAAAAAAAAAA",
        "IBANIBANIBANIBANIBANIBANAAA", 0.5f);
        Heir heir2 = new Heir("Giacomo", "Poretti", "BBBBBBBBBBBBBBBB",
        "IBANIBANIBANIBANIBANIBANBBB", 0.3f);
        Heir heir3 = new Heir("Francesco", "Totti", "CCCCCCCCCCCCCCCC",
        "IBANIBANIBANIBANIBANIBANCCC", 0.1f);

        assertEquals(heir1, heirList.get(0));
        assertEquals(heir2, heirList.get(1));
        assertEquals(heir3, heirList.get(2));  
    }

    @Test(expected = FileFormatException.class)
    public void badFormat() throws InvalidHeirException, FileNotFoundException, FileFormatException{
        Parser parser = new Parser();
        parser.parseHeirs(dir + "HeirParserTestCase3.txt"); 
    }

    @Test(expected = FileNotFoundException.class)
    public void badLocation() throws InvalidHeirException, FileNotFoundException, FileFormatException{
        Parser parser = new Parser();
        parser.parseHeirs(dir + "AAAHeirParserTestCase.txt");
    }

    @Test
    public void badCf() throws FileNotFoundException, FileFormatException{
        boolean exception = false;
        Parser parser = new Parser();
        try{
            parser.parseHeirs(dir + "HeirParserTestCase4.txt");
        }catch(InvalidHeirException e){
            assertEquals(e.getMessage(), "Invalid cf format");
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void badIban() throws FileNotFoundException, FileFormatException{
        boolean exception = false;
        Parser parser = new Parser();
        try{
            parser.parseHeirs(dir + "HeirParserTestCase5.txt");
        }catch(InvalidHeirException e){
            assertEquals(e.getMessage(), "Invalid IBAN format");
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void shareGreaterThan1() throws FileNotFoundException, FileFormatException{
        boolean exception = false;
        Parser parser = new Parser();
        try{
            parser.parseHeirs(dir + "HeirParserTestCase6.txt");
        }catch(InvalidHeirException e){
            assertEquals(e.getMessage(), "Heir's share must be between 0 and 1");
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void negativeShare() throws FileNotFoundException, FileFormatException{
        boolean exception = false;
        Parser parser = new Parser();
        try{
            parser.parseHeirs(dir + "HeirParserTestCase7.txt");
        }catch(InvalidHeirException e){
            assertEquals(e.getMessage(), "Heir's share must be between 0 and 1");
            exception = true;
        }
        assertTrue(exception);
    }
}
