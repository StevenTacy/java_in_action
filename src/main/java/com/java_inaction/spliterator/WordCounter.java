package com.java_inaction.spliterator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WordCounter {
    private final int count;
    private final boolean lastSpace;

    /**
     * using immutable state to make parallelism possible -> final field for both class members
     */
    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(count, true);
        } else {
            return lastSpace ? new WordCounter(count + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter other) {
        return new WordCounter(count + other.count, lastSpace);
    }
}
