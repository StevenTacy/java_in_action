package com.java_inaction.spliterator;

import java.time.Duration;
import java.time.Instant;
import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CountWordsImpl {

    final static String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita " +
                    "mi  ritrovai in una  selva oscura" +
                    " ché la  dritta via era   smarrita ";

    static void main() {
        printWordCounter();
    }

    public static int countWordsIteratively(String str) {
        var counter = 0;
        var lastWhitespace = true;
        for (char c : str.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastWhitespace = true;
            } else {
                if (lastWhitespace) counter++;
                lastWhitespace = false;
            }
        }
        return counter;
    }

    public static int countWordsWCharArr() {
        Instant start = Instant.now();
        Stream<Character> charStream1 = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        WordCounter wordCounter = charStream1.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println(Duration.between(start, Instant.now()).toMillis());
        return wordCounter.getCount();
    }

    public static int countWordsSpliterator() {
        Instant start = Instant.now();
        Spliterator<Character> spliterator = new WordCountsSpliterator(SENTENCE);
        Stream<Character> charStream = StreamSupport.stream(spliterator, true);
        WordCounter wordCounter = charStream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println(Duration.between(start, Instant.now()).toMillis());
        return wordCounter.getCount();
    }

    public static void printWordCounter() {
        System.out.println("counts of word in SENTENCE: " + countWordsIteratively(SENTENCE));
        System.out.println("counts of word in SENTENCE: " + countWordsWCharArr());
        System.out.println("counts of word in SENTENCE: " + countWordsSpliterator());
    }
}
