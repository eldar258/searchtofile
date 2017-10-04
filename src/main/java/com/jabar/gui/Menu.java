package com.jabar.gui;

import com.jabar.search.Search;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Class Menu. Displayed in tab.
 *
 * @author edzabarov
 * @since 28.09.2017
 */
public class Menu {
    /**
     * menu displayed in table
     */
    private JPanel menu;
    /**
     * tree files.
     */
    private JTree fileTree;
    /**
     * the text of the found file.
     */
    private JTextArea textInFilesArea;
    /**
     * window for selecting files.
     */
    private static JFileChooser fileChooser = new JFileChooser();
    /**
     * selected directory.
     */
    private File directory;
    /**
     * word for search.
     */
    private JTextField searchField;
    /**
     * Extension of the files to be searched.
     */
    private JTextField fileExtensionField;
    /**
     * progress bar.
     */
    private JProgressBar progressBar;

    static {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }
    /**
     * Constructor. creates the menu.
     */
    public Menu() {
        this.menu = new JPanel();
            JPanel searchPanel = new JPanel();
                JButton chooseDirButton = new JButton("Choose a directory");
                JLabel searchLabel = new JLabel("Enter text to search for:");
                searchField = new JTextField(20);
                JLabel fileExtensionLabel = new JLabel("file extension:");
                fileExtensionField = new JTextField("log", 5);
                JButton searchButton = new JButton("search");
                progressBar = new JProgressBar();
            JPanel resultPanel = new JPanel();
                JPanel fileTreePanel = new JPanel();
                    JLabel fileTreeLabel = new JLabel("Directory:");
                    fileTree = new JTree();
                    JScrollPane fileTreeScrollPane = new JScrollPane(fileTree);
                JPanel textInFilesPanel = new JPanel();
                    JLabel textInFilesLabel = new JLabel("Text file:");
                    textInFilesArea = new JTextArea(5, 30);
                    JScrollPane textInFilesScrollPane = new JScrollPane(textInFilesArea);
        //set Layouts
        this.menu.setLayout(new BoxLayout(this.menu, BoxLayout.Y_AXIS));
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.X_AXIS));

        fileTreePanel.setLayout(new BorderLayout());
        textInFilesPanel.setLayout(new BorderLayout());

        //add Listeners
        chooseDirButton.addActionListener(new ChooseDirButtonListener());
        searchButton.addActionListener(new SearchButtonListener());
        fileTree.addTreeSelectionListener(new FileTreeSelectionListener());

        //add components
        searchPanel.add(chooseDirButton);
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(fileExtensionLabel);
        searchPanel.add(fileExtensionField);
        searchPanel.add(searchButton);
        searchPanel.add(progressBar);

        fileTree.setModel(null); //for the initial tree to be empty
        fileTreePanel.add(fileTreeLabel, BorderLayout.NORTH);
        fileTreePanel.add(fileTreeScrollPane, BorderLayout.CENTER);
        textInFilesPanel.add(textInFilesLabel, BorderLayout.NORTH);
        textInFilesPanel.add(textInFilesScrollPane, BorderLayout.CENTER);

        resultPanel.add(fileTreePanel);
        resultPanel.add(textInFilesPanel);

        this.menu.add(searchPanel);
        this.menu.add(resultPanel);
    }
    public JPanel getMenu() {
        return this.menu;
    }

    /**
     * Listener, which selects a directory.
     */
    private class ChooseDirButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (fileChooser.showDialog(null, "Choose directory") == JFileChooser.APPROVE_OPTION) {
                directory = fileChooser.getSelectedFile();
                fileChooser.setCurrentDirectory(null); //reset current directory
            }
        }
    }

    /**
     * Listener, which searches for files.
     */
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchWord = searchField.getText();
            String fileExtension = fileExtensionField.getText();
            if (progressBar.isIndeterminate()) {
                JOptionPane.showMessageDialog(null, "The search is already under way. Please wait.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (searchWord.isEmpty() || fileExtension.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There is no word or file extension to search for files.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (directory != null) {
                progressBar.setIndeterminate(true);
                Search search = new Search(progressBar, fileTree, directory, fileExtension, searchWord);
                Thread searchThread = new Thread(search);
                searchThread.start();
            } else {
                JOptionPane.showMessageDialog(null, "The search directory is not selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Listener, which opens the file in the textInFilesArea.
     */
    private class FileTreeSelectionListener implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            File file = (File) fileTree.getLastSelectedPathComponent();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    BufferedReader bf = null;
                    try {
                        bf = new BufferedReader(new FileReader(file));
                        textInFilesArea.read(bf, null);
                        bf.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}
