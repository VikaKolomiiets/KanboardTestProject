package api.utils;


import java.util.Random;

public class DataTests {
    public static String addUniqueSuffix(String name){
        Random random = new Random();
        return name + random.nextInt(1000);
    }
}
