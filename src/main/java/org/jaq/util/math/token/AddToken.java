package org.jaq.util.math.token;

import org.jetbrains.annotations.NotNull;

public class AddToken<T> extends OperatorToken<T>{
    private enum Type {
        DOUBLE("Double"),
        INT("Integer")
        ;
        private final String type;
        private Type(final String type){
            this.type = type;
        }
    }
    T nullInstance;

    private Type type;

    private void initType(){
        if(nullInstance instanceof Double) type = Type.DOUBLE;
        if(nullInstance instanceof Integer) type = Type.INT;
    }

    public AddToken(){
        super("+");
        if(!(
            nullInstance instanceof Double || 
            nullInstance instanceof Integer 
        )) throw new RuntimeException("invalid type");
        initType();
    }
    @SuppressWarnings("uncheck")

    @Override
    public T eval(@NotNull OperandToken a, @NotNull OperandToken b) {
        if(type == Type.DOUBLE) {
            Double c = Double.parseDouble(a.toString()) + Double.parseDouble(b.toString());
            return (T)c;
        }
        throw new RuntimeException();
    }

    @Override
    public int prec(){
        return 1;
    }
    
}
