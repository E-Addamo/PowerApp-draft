package com.powerapp.ParserTests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;

import com.powerapp.Parser;
import com.powerapp.SubFund;

import com.powerapp.InvalidSubFundException;
import com.powerapp.FileFormatException;
import java.io.FileNotFoundException;

public class SubFundParserTest {

    private static final String dir = System.getProperty("user.dir") + "/src/test/java/com/powerapp/ParserTests/";


    @Test
    public void oneSubFund() throws InvalidSubFundException, FileFormatException, FileNotFoundException{
        Parser parser = new Parser();
        ArrayList<SubFund> subFundList = parser.parseSubFunds(dir + "SubFundParserTestCase1.txt");
        SubFund subFund = new SubFund("AAAAAAAAAAAA", 20.0f);
        assertEquals(subFundList.get(0), subFund);
    }

}
