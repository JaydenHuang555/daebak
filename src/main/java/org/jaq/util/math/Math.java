package org.jaq.util.math;

import org.jaq.util.OrderedList;
import org.jaq.util.math.token.Token;
import org.jaq.util.Util;
import org.jaq.util.Stack;

public class Math {


    private static OrderedList<Token> tokens;
    private static Stack<Token> operators;

    private static int prec(char c){
        switch(c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default: return 0;
        }
    }

    private static void digest(String eq){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < eq.length(); i++){
            char c = eq.charAt(i);
            if(Util.isNum(c) || c == '.') builder.append(c);
            if(prec(c) != 0){
                if()
            }
            
        }
        r

    }
    
    public static double eval(String eq){
        tokens = new OrderedList<>();
        operators = new Stack<>();
        OrderedList<Token> tokens = new OrderedList<>();
        return 0;
    }

}
