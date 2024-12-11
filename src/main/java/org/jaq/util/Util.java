package org.jaq.util;

public class Util {
    public final static boolean isNum(final char c){
        return 47 < c && c < 58;
    }

    public final static boolean isNum(String s){
        for(int i = 0; i < s.length(); i++) if(!isNum(s.charAt(i))) return false;
        return true;
    }

}
