package org.jaq.util.math.token;

import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus.OverrideOnly;

import com.google.j2objc.annotations.ObjectiveCName;

public class AddToken extends OperatorToken {

    public AddToken(){
        super("+");
    }
    @Override
    public OperandToken evalf(@NotNull OperandToken a, @NotNull OperandToken b){
        return new OperandToken(
            a.valf() + b.valf()
        );
    }

    @Override
    public OperandToken evall(@NotNull OperandToken a, @NotNull OperandToken b){
        return new OperandToken(a.vall() + b.vall());
    }

    

    @Override
    public int prec(){
        return 1;
    }
}
