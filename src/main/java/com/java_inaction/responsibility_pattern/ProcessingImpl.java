package com.java_inaction.responsibility_pattern;

import java.util.function.UnaryOperator;

public class ProcessingImpl {

    public void processingInput() {
        UnaryOperator<String> headerProcessor = (input) -> "From Raoul, Mario and Alan: " + input;
        UnaryOperator<String> spellCheckerProcessor = (input) -> input.replaceAll("labda", "lambda");
        var pipeline = headerProcessor.andThen(spellCheckerProcessor);
        String result = pipeline.apply("Aren't labdas really sexy?!!");
    }
}
