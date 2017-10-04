package com.jabar.search;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test Search
 */
public class SearchTest {
    /**
     * test filter()
     */
    @Test
    public void whenInDirectorySearchFilesThenReturnTXTFileContainingWordTest() {
        File directory =  new File("src/test/resources/searchesDirectory");
        Search search = new Search(null, null, directory, "txt", "test");
        Set files = null;
        try {
            files = Files.walk(directory.toPath()).filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toCollection(HashSet::new));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Set<File> expected = search.filter(files);
        Set<File> result = new HashSet<>();
        result.add(new File("src/test/resources/searchesDirectory/text.txt"));
        result.add(new File("src/test/resources/searchesDirectory/text2.txt"));
        assertThat(expected, is(result));
    }
    /**
     * test check()
     */
    @Test
    public void whenCheckFileContainingWordTestAndExcThenReturnTrue () {
        File file =  new File("src/test/resources/searchesDirectory/text.txt");
        Search search = new Search(null, null, file, "txt", "test");
        boolean expected = search.check(file);
        boolean result = true;
        assertThat(expected, is(result));
    }

    /**
     * test indexOfIgnoreCase()
     */
    @Test
    public void whenIndexOfIgnoreCaseInStringThenReturnIndex3 () {
        String ptr = "ur0tEsT0erofieo";
        Search search = new Search(null, null, null, null, null);
        int result = 3;
        int expected = search.indexOfIgnoreCase(ptr, "test");
        assertThat(expected, is(result));
    }
}
