package org.jaq.util.math.token.operator;

import org.jaq.util.math.token.OperandToken;
import org.jetbrains.annotations.NotNull;

public class DivToken extends OperatorToken {

    public DivToken(){
        super("/");
    }
    @Override
    public OperandToken evalf(@NotNull OperandToken a, @NotNull OperandToken b){
        return new OperandToken(
            a.valf() * b.valf()
        );
    }

    @Override
    public OperandToken evalh(@NotNull OperandToken a, @NotNull OperandToken b){
        return new OperandToken(a.valh() / b.valh());
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
