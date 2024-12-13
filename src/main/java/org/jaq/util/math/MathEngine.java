package org.jaq.util.math;

import org.checkerframework.checker.units.qual.s;
import org.jaq.daebak.Global;
import org.jaq.util.OrderedList;
import org.jaq.util.Stack;
import org.jaq.util.Util;
import org.jaq.util.math.token.Token;
import org.jaq.util.math.token.operator.AddToken;
import org.jaq.util.math.token.operator.DivToken;
import org.jaq.util.math.token.operator.ModToken;
import org.jaq.util.math.token.operator.MultiToken;
import org.jaq.util.math.token.operator.OperatorToken;
import org.jaq.util.math.token.operator.SubToken;
import org.jetbrains.annotations.NotNull;
import org.jaq.util.math.token.OperandToken;

public final class MathEngine<T> {


    private OrderedList<Token> digested = null;

    private static enum Type {
        DOUBLE,
        LONG
    }

    private OperatorToken getOperatorTokenType(char c){
        switch(c){
            case '+': return new AddToken();
            case '-': return new SubToken();
            case '*': return new MultiToken();
            case '/': return new DivToken();
            case '%': return new ModToken();
            default: throw new RuntimeException(String.format("%c is not a valid operator flag", c));
        }
    }

    private int prec(char c){
        switch(c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;    
            default: return 0;
        }
    }

    private OrderedList<Token> digest(@NotNull Type type, @NotNull String eq){
        Stack<OperatorToken> operators = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < eq.length(); i++){
            char c = eq.charAt(i);
            Global.logf("c is %c", c);
            boolean isNum = Util.isNum(c);
            isNum = type == Type.DOUBLE ? (isNum || c == '.') : isNum;
            if(isNum) {
                Global.logf("appending %c to builder", c);
                builder.append(c);
            }
            if(prec(c) != 0){
                if(builder.length() > 0){
                    digested.add(new OperandToken(builder.toString()));
                    builder = new StringBuilder();
                    Global.logf("appending %s", builder.toString());
                }
                /* meant for handling negatives, suject to change */
                else if(c == '-'){
                    Global.logf("appending %c to builder", c);
                    builder.append(c);
                    break;
                }
                Global.logf("handling operator %c", c);
                while(!operators.isEmpty() && operators.peek().prec() >= prec(c))
                    digested.add(operators.pop());
                operators.push(getOperatorTokenType(c));
            }
            
        }
        if(!builder.isEmpty()) digested.add(new OperandToken(builder.toString()));
        while(!operators.isEmpty()) digested.add(operators.pop());
        for(int i = 0; i < digested.getSize(); i++) Global.logf("token at %d is %s", i, digested.get(i).toString());
        return digested;
    }
    
    private String eval(@NotNull Type type, @NotNull String eq){
        digested = new OrderedList<>();
        operators = new Stack<>();
        digested = digest(type, eq);
        Stack<OperandToken> stack = new Stack<>();
        Global.logf("tokens size is %d", digested.getSize());

        for(int i = 0; i < digested.getSize(); i++){
            if(digested.get(i) instanceof OperandToken) stack.push((OperandToken)digested.get(i));
            if(digested.get(i) instanceof OperatorToken){
                OperandToken right = stack.pop();
                OperandToken left = stack.pop();
                switch(type){
                    case DOUBLE:
                        stack.push(((OperatorToken)digested.get(i)).evalf(right, left));
                        break;
                    case LONG:
                        stack.push(((OperatorToken)digested.get(i)).evall(right, left));
                        break;
                    default:
                        break;
                }
                if(!stack.isEmpty())
                Global.logf("peek is now %s ", stack.peek());
            }
        }

       // return stack.pop().toString();
        return stack.pop().toString();
    }

    public double evalf(@NotNull String eq){
        return Double.parseDouble(eval(Type.DOUBLE, eq));
    }

    public double evall(@NotNull String eq){
        return Long.parseLong(eval(Type.LONG, eq));
    }

}
