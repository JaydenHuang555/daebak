package org.jaq.util.math.token;

public class OperandToken extends Token {

    public OperandToken(String val) {
        super(val);
    }

    public OperandToken(Object obj){
        super(obj.toString());
    }

    public double valf(){
        return Double.parseDouble(val);
    }

    public short valh(){
        return Short.parseShort(val);
    }

    public long vall(){
        return Long.parseLong(val);
    }
    
}
