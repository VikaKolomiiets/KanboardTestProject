package ui.utils;

import org.testng.annotations.DataProvider;

public class DataForTests {



    @DataProvider(name = "correct-both-input-data")
    public Object[][] dataProvFunc(){
        return new Object[][]{
                {"admin", "admin"}};
    }
}
