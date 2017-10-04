package com.jabar.search;

import java.io.*;
import java.util.Set;

/**
 * Class SearchFilterFile. Helps the tree to display the required branches.
 *
 * @author edzabarov
 * @since 30.09.2017
 */
public class SearchFilterFile implements FileFilter {
    /**
     * founds files.
     */
    private Set<File> foundsFiles;
    /**
     *  To prevent unnecessary operations in the already formed tree (without it, the tree very much inhibits very much).
     */
    private boolean close = false;
    /**
     * Constructor.
     *
     * @param foundsFiles -
     */
    public SearchFilterFile(Set<File> foundsFiles) {
        this.foundsFiles = foundsFiles;
    }

    /**
     * close filter when it is no longer needed.
     */
    public void close() {
        this.close = true;
    }
    @Override
    public boolean accept(File dir) {
        return dir.isDirectory() ? close || dive(dir) : foundsFiles.contains(dir);
    }

    /**
     * It is necessary for not displaying empty folders in a tree.
     *
     * @param dir -
     * @return - Returns false if there are no files in the directory.
     */
    public boolean dive(File dir) {
        boolean result = false;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    result = foundsFiles.contains(file);
                } else {
                    result = dive(file);
                }
                if (result) break;
            }
        }
        return result;
    }
}
