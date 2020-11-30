package by.issoft.app.utils;

import java.util.Random;

public class GenericUtils {

    public static String getRandomEmail(String baseEmail) {
        String[] emailParts = baseEmail.split("@");
        return emailParts[0]+"+"+str()+"@"+emailParts[1];
    }

    public static String str(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
