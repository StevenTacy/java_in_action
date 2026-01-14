package com;

import java.io.File;

public class Fileimpl {
    public File[] fileList() {
        // stream using :: should pass in the object name
        File[] files = new File(".").listFiles(File::isHidden);
        return files;
    }
}
