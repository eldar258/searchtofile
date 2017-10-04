package com.jabar.search;

import com.jabar.gui.FileTreeModel;

import javax.swing.*;
import javax.swing.tree.TreeSelectionModel;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class Search.
 *
 * @author edzabarov
 * @since 29.09.2017p
 */
public class Search implements Runnable {
    /**
     * TREE.
     */
    private JTree fileTree;
    /**
     * selected directory.
     */
    private File directory;
    /**
     * file extension.
     */
    private String fileExtension;
    /**
     * search word.
     */
    private String searchWord;
    /**
     * progressBar;
     */
    private JProgressBar progressBar;

    /**
     * Constructor. Accept all fields.
     * @param progressBar -
     * @param fileTree -
     * @param directory -
     * @param fileExtension -
     * @param searchWord -
     */
    public Search(JProgressBar progressBar, JTree fileTree, File directory, String fileExtension, String searchWord) {
        this.progressBar = progressBar;
        this.fileTree = fileTree;
        this.directory = directory;
        this.fileExtension = fileExtension;
        this.searchWord = searchWord;
    }

    /**
     * This method opens a thread. This method find all file and
     * puts them in the tree.
     */
    public void run() {
        System.out.println("START");
        Set<File> files = null;
        try {
            files = Files.walk(directory.toPath()).filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toCollection(HashSet<File>::new));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        SearchFilterFile filter = new SearchFilterFile(filter(files));
        FileTreeModel model = new FileTreeModel(new FileThatReturnsName(directory.getPath()), filter);
        fileTree.setModel(model);
        fileTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        for(int i = 0; i < fileTree.getRowCount(); i++) {
            fileTree.expandRow(i);
        }
        filter.close();
        progressBar.setIndeterminate(false);
        System.out.println("END");
    }

    /**
     * Filters files that satisfy the condition.
     * @param files - all found files.
     * @return - satisfying the condition.
     */
    public Set<File> filter (Set<File> files) {
        Set<File> result = new HashSet<>();
        for (File file : files) {
            if (check(file)) {
                result.add(file);
            }
        }
        return result;
    }

    /**
     * Check the file for the required condition.
     * @param file - checked file.
     * @return - true if the file matches the condition.
     */
    public boolean check (File file) {
        int index;
        boolean result = false;
        if ((index = file.getName().lastIndexOf(".")) != -1 && fileExtension.equalsIgnoreCase(file.getName().substring(index + 1))) {
            if (indexOfIgnoreCase(file.getName(), searchWord) != -1) {
                result = true;
            } else {
                try {
                    BufferedReader bf = new BufferedReader(new FileReader(file));
                    StringBuilder sb = new StringBuilder();
                    while (bf.ready() && (sb.append(bf.readLine())).length() > 0) {
                        if (indexOfIgnoreCase(sb, searchWord) != -1) {
                            bf.close();
                            result = true;
                            break;
                        }
                        sb.setLength(0);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * Check for the presence of words in the text without taking into account the register.
     * @param sequence - text.
     * @param word - searchWord.
     * @return - -1 if word does not find. If word find return index.
     */
    public int indexOfIgnoreCase(CharSequence sequence, String word) {
        try {
            int i = 0;
            int j = 0;
            int length = word.length() - 1;
            while (true) {
                if (Character.toLowerCase(sequence.charAt(i)) == Character.toLowerCase(word.charAt(j))) {
                    if (j == length) {
                        return i - length;
                    }
                    j++;
                } else {
                    j = 0;
                }
                i++;
            }
        } catch (IndexOutOfBoundsException ex) {
            return -1;
        }
    }
}
