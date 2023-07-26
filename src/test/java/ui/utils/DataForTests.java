package ui.utils;

import org.testng.annotations.DataProvider;

public class DataForTests {



    @DataProvider(name = "correct-both-input-data")
    public Object[][] dataProvCorrect(){
        return new Object[][]{
                {"admin", "admin"}};
    }
    @DataProvider(name = "incorrect-both-input-data")
    public Object[][] dataProvIncorrectBothDataInput(){
        return new Object[][]{
                {"admin", "dmina"},
                {"midna", "admin"},
                {"ad", "min"},
                {"admin", ""},
                {"", "admin"}};
    }

    @DataProvider(name = "input-one-data")
    public Object[][] dataProvOneDataInput(){
        return new Object[][]{
                {"admin"}, {"admiral"}, {""}, {"null"}};
    }


}
