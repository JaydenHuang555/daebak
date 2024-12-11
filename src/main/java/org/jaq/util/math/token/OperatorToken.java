package org.jaq.util.math.token;

import org.jetbrains.annotations.NotNull;

public abstract class OperatorToken<T> extends Token{

    public OperatorToken(@NotNull String val) {
        super(val);
    }

    public abstract T eval(@NotNull OperandToken a, @NotNull OperandToken b);


    public abstract int prec();
    
}
