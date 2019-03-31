package nwawsoft.util;

public class MoreStringFunctions {
    public MoreStringFunctions() {

    }

    public boolean isOnlyUpperCase(String pInput) {
        char[] inputChars = pInput.toCharArray();
        for (int i = 0; i < inputChars.length; i++) {
            if (!Character.isUpperCase(inputChars[i])) {
                return false;
            }
        }
        return true;
    }
}