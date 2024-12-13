package org.jaq.util.math.token;


public abstract class Token {

    protected final String val;

    public Token(final String val){
        this.val = val;
    }

    @Override
    public String toString(){
        return val;
    }
    
}
