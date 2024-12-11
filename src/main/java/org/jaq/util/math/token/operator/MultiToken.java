package org.jaq.util.math.token.operator;

import org.jaq.util.math.token.OperandToken;
import org.jetbrains.annotations.NotNull;

public class MultiToken extends OperatorToken {

    public MultiToken(){
        super("+");
    }
    @Override
    public OperandToken evalf(@NotNull OperandToken a, @NotNull OperandToken b){
        return new OperandToken(
            a.valf() * b.valf()
        );
    }

    @Override
    public OperandToken evall(@NotNull OperandToken a, @NotNull OperandToken b){
        return new OperandToken(a.vall() * b.vall());
    }

    @Override
    public int prec(){
        return 2;
    }
}
