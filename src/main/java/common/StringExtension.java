package common;

import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;
import java.util.Random;

public class StringExtension {
    public static String generateString(){
        String alphChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * alphChars.length());
            salt.append(alphChars.charAt(index));
        }
        return salt.toString();
    }

    public static String generateUrl(String url){
        return String.format("%1$s%2$s", Configurations.getValueByKey("hostApi"), url);
    }

    public static String baseEncode(){
        return String.format("Basic %1$s", new String(Base64.encodeBase64(
                String.format("%1$s:%2$s", Configurations.getValueByKey("user"), Configurations.getValueByKey("password")).getBytes()
        )));
    }

    public static String formatMessage(Exception ex){
        int index = ex.getMessage().indexOf("\n");
        index = index != -1 ? index : ex.getMessage().length();
        return ex.getClass().toString() + ": " + ex.getMessage().substring(0, index);
    }


    public static String charsToString(String[] arrayOfChars){
        Integer[] chars = Arrays.stream(arrayOfChars).map(Integer::valueOf).toArray(Integer[]::new);
        StringBuilder result = new StringBuilder();
        for (int charCode : chars) {
            result.append(Character.toString((char) charCode));
        }
        return result.toString().trim();
    }

}
