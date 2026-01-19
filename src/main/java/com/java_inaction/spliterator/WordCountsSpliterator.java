package com.java_inaction.spliterator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Spliterator;
import java.util.function.Consumer;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class WordCountsSpliterator implements Spliterator<Character> {

    private final String str;
    private int curChar;

    /**
     * check if current char position smaller than total lenth of str
     */
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(str.charAt(curChar++));
        return curChar < str.length();
    }

    public Spliterator<Character> trySplit() {
        int curSize = str.length() - this.curChar;
        if (curSize < 10) return null;
        for (int splitPos = curSize / 2 + curChar; splitPos < str.length(); splitPos++) {
            if (Character.isWhitespace(str.charAt(splitPos))) {
                Spliterator<Character> spliterator = new WordCountsSpliterator(str.substring(curChar, splitPos));
                curChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    public long estimateSize() {
        return str.length() - curChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
