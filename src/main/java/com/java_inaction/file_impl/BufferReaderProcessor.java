package com.java_inaction.file_impl;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferReaderProcessor {
    public String process(BufferedReader br) throws IOException;
}
