package org.jaq.util.math.token;

public class OperandToken extends Token {

    public OperandToken(String val) {
        super(val);
    }

    public OperandToken(Object obj){
        super(obj.toString());
    }

    
    
}
