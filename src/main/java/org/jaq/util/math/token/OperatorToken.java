package org.jaq.util.math.token;

import org.jetbrains.annotations.NotNull;

public abstract class OperatorToken extends Token{

    public OperatorToken(@NotNull String val) {
        super(val);
    }

    public abstract OperandToken eval(@NotNull OperandToken a, @NotNull OperandToken b);


    public abstract int prec();
    
}
