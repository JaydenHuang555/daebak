package org.jaq.util.math;

import org.jaq.util.OrderedList;
import org.jaq.util.Stack;
import org.jaq.util.Util;
import org.jaq.util.math.token.Token;
import org.jaq.util.math.token.OperandToken;
import org.jaq.util.math.token.OperatorToken;
import org.jaq.util.math.token.AddToken;

public final class MathEngine<T> {
    private OrderedList<Token> tokens;
    private Stack<Token> operators;


    private OperatorToken<T> getOperatorTokenType(char c){
        switch(c){
            case '+': return new AddToken<T>();
            default: return null;
        }
    }

    private int prec(char c){
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

    private void handleOperator(char c){
        if(operators.isEmpty()){
            operators.push(getOperatorTokenType(c));
            return;
        }
        if(((OperatorToken)operators.peek()).prec() > prec(c)){
            
        }
    }

    private void digest(String eq){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < eq.length(); i++){
            char c = eq.charAt(i);
            if(Util.isNum(c) || c == '.') builder.append(c);
            if(prec(c) != 0){
                if(builder.length() > 0){
                    tokens.add(new OperandToken(builder.toString()));
                    builder = new StringBuilder();
                }
                else if(c == '-'){
                    builder.append(c);
                    break;
                }
                handleOperator(c);
            }
            
        }
        

    }
    @SuppressWarnings("uncheck")
    public  T eval(String eq){
        tokens = new OrderedList<>();
        operators = new Stack<>();
        return null;
    }
}
