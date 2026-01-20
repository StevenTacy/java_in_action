package com.java_inaction.responsibility_pattern;

import lombok.Data;

@Data
public abstract class ProcessingObject<T> {
    protected ProcessingObject<T> successor;

    public T handle(T input) {
        T result = handleWork(input);
        if (this.successor != null) {
            return this.successor.handle(input);
        }
        return result;
    }

    abstract T handleWork(T input);
}
