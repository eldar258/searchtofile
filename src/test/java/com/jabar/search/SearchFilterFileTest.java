package com.jabar.search;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import static org.hamcrest.core.Is.is;
//import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * test SearchFilterFileClass
 */
public class SearchFilterFileTest {
    /**
     * test acept()
     */
    @Test
    public void whenFilterSearchFilesThenReturnTXTFileContainingWordTest() {
        Set<File> files = new HashSet<>();
        files.addAll(Arrays.asList(new File("src/test/resources/searchesDirectory/text.txt"),
                new File("src/test/resources/searchesDirectory/text2.txt"),
                new File("src/test/resources/searchesDirectory/text3.txt")));
        FileFilter filter = new SearchFilterFile(files);
        File file = new File("src/test/resources/searchesDirectory/");
        File[] expected = file.listFiles(filter);
        File[] result = {new File("src/test/resources/searchesDirectory/text.txt"),
                new File("src/test/resources/searchesDirectory/text2.txt"),
                new File("src/test/resources/searchesDirectory/text3.txt")};
        assertThat(expected, is (result));
    }

    /**
     * test dive()
     */
    @Test
    public void whenFileDiveThenReturnOnlyNeedsFilesTXTAndContainingWordTest() {
        Set<File> files = new HashSet<>();
        files.add(new File("src/test/resources/searchesDirectory/text.txt"));
        SearchFilterFile filter = new SearchFilterFile(files);
        boolean expected = filter.dive(new File("src"));
        boolean result = true;
        assertThat(expected, is (result));
    }
}
