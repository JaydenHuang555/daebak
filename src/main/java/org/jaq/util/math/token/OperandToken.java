package org.jaq.util.math.token;

import it.unimi.dsi.fastutil.doubles.Double2BooleanAVLTreeMap;

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
    
}
