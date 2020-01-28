package fz.idle.chat.core;

public class StringUtils {

    public static String changeFirstCharacterCase(String str, boolean capitalize) {
        char baseChar = str.charAt(0);
        char updatedChar;
        if (capitalize) {
            updatedChar = Character.toUpperCase(baseChar);
        } else {
            updatedChar = Character.toLowerCase(baseChar);
        }

        if (baseChar == updatedChar) {
            return str;
        } else {
            char[] chars = str.toCharArray();
            chars[0] = updatedChar;
            return new String(chars, 0, chars.length);
        }
    }
}
