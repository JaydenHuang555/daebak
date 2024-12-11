package org.jaq.util.math;

import org.jaq.util.OrderedList;
import org.jaq.util.math.token.Token;
import org.jetbrains.annotations.NotNull;
import org.jaq.util.Util;
import org.jaq.util.Stack;

public class Math {

    private final static MathEngine<Double> engine = new MathEngine<>();
    
    public static String eval(@NotNull String eq){
        return engine.eval(eq);
    }

}
