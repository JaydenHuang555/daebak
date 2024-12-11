package org.jaq.util;

import org.jetbrains.annotations.NotNull;

public enum PropertyType {
    OS_NAME("os.name")
    ;
    public final String key;
    private PropertyType(@NotNull final String key){
        this.key = key;
    }
    public final String toString(){
        return key;
    }

    public final String key(){
        return this.key;
    }

}
