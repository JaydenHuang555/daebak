package org.jaq.util.math.token.operator;

import org.jaq.util.math.token.OperandToken;
import org.jaq.util.math.token.Token;
import org.jetbrains.annotations.NotNull;

public abstract class OperatorToken extends Token{

    public OperatorToken(@NotNull String val) {
        super(val);
    }

    public abstract OperandToken evalf(@NotNull OperandToken a, @NotNull OperandToken b);

    public abstract OperandToken evall(@NotNull OperandToken a, @NotNull OperandToken b);


    public abstract int prec();
    
}
