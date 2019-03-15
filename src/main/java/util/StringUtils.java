package util;

public class StringUtils {

    public static boolean isNullOrEmpty( String s ) {
        return s == null || s.length() <= 0;
    }

    public static boolean isNullOrEmptyArray( String[] strArray ) {
        return strArray == null || strArray.length <= 0;
    }
}