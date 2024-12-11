package org.jaq.util.math;

import org.checkerframework.checker.units.qual.s;
import org.jaq.util.OrderedList;
import org.jaq.util.Stack;
import org.jaq.util.Util;
import org.jaq.util.math.token.Token;
import org.jetbrains.annotations.NotNull;
import org.jaq.util.math.token.OperandToken;
import org.jaq.util.math.token.OperatorToken;
import org.jaq.util.math.token.AddToken;

public final class MathEngine<T> {
    private OrderedList<Token> tokens;
    private Stack<OperatorToken> operators;


    private OperatorToken getOperatorTokenType(char c){
        switch(c){
            case '+': return new AddToken();
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
        else if((operators.peek()).prec() > prec(c)){
            while(operators.peek().prec() > prec(c)) tokens.add(operators.pop());
            operators.push(getOperatorTokenType(c));
        }
        else if(operators.peek().prec() < prec(c)) tokens.add(getOperatorTokenType(c));
        else operators.push(getOperatorTokenType(c));
    }

    private void digest(@NotNull String eq){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < eq.length(); i++){
            char c = eq.charAt(i);
            if(Util.isNum(c) || c == '.') builder.append(c);
            if(prec(c) != 0){
                if(builder.length() > 0){
                    tokens.add(new OperandToken(builder.toString()));
                    builder = new StringBuilder();
                }
                /* meant for handling negatives, suject to removal */
                else if(c == '-'){
                    builder.append(c);
                    break;
                }
                handleOperator(c);
            }
            
        }
        
        while(!operators.isEmpty()) tokens.add(operators.pop());

    }
    )
    public double eval(@NotNull String eq){
        tokens = new OrderedList<>();
        operators = new Stack<>();
        digest(eq);
        Stack<OperandToken> stack = new Stack<>();

        for(int i = 0; i < tokens.getSize(); i++){
            if(tokens.get(i) instanceof OperandToken) stack.push((OperandToken)tokens.get(i));
            if(tokens.get(i) instanceof OperatorToken){
                OperandToken right = stack.pop();
                OperandToken left = stack.pop();
                stack.push(((OperatorToken)tokens.get(i)).evalf(right, left));
            }
        }

        return stack.pop().valf();
    }
}
