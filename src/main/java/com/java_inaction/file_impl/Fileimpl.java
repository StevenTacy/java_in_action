package com.java_inaction.file_impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class Fileimpl {
    public File[] fileList() {
        // stream using :: should pass in the object name
        File[] files = new File(".").listFiles(File::isHidden);
        return files;
    }

    public String processFile(BufferReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    public void readOneLine() throws IOException {
        // in lambda remember utilize the input object method to meet the definition of the functional interface
        // in this example, BufferReaderProcessor has one method process which takes in a BufferReader object and return String
        // that said, i can utilize any method that return string in BufferReader -> readLine()
        String oneLineByLambda = processFile(BufferedReader::readLine);
        String oneLineByAnonymous = processFile(new BufferReaderProcessor() {
            @Override
            public String process(BufferedReader br) throws IOException {
                return br.readLine();
            }
        });
    }

    public void readTwoLines() throws IOException {
        String twoLinesByLambda = processFile(br -> br.readLine() + br.readLine());
        String twoLinesByAnonymous = processFile(new BufferReaderProcessor() {
            public String process(BufferedReader br) throws IOException {
                return br.readLine() + br.readLine();
            }
        });
    }

    /**
     * using try-catch in functional interface
     * in case the parent interface has tight and not throwing exception
     * can handle exception by throwing try-catch in ur own block
     */
    public void tryCatchBufferReader() {
        Function<BufferedReader, String> f = b -> {
            try {
                return b.readLine();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public long uniqueNumberWords() {
        long count = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            count = lines.flatMap(l -> Arrays.stream(l.split(" "))).distinct().count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return count;
    }
}