package com.jabar.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Class Main window.
 *
 * @author edzabarov
 * @since 28.09.2017
 */
public class MainWindow {
    /**
     * main frame, window.
     */
    private JFrame window;

    /**
     * Displays the window and launches the main components of the windows.
     */
    public void go() {
        this.window = new JFrame("Search to file");

        MainMenu mainPanel = new MainMenu();
        this.window.add(mainPanel.getMainMenu(), BorderLayout.CENTER);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.pack();
        //this.window.setSize(800, 600);
        this.window.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.window.setVisible(true);
    }
}
