package com.jabar.start;

import com.jabar.gui.MainWindow;

/**
 * Class Start.
 *
 * @author edzabarov
 * @since 28.09.2017
 */
public class Start {
    /**
     *  Starts the main window.
     * @param args -
     */
    public static void main(String[] args) {
        System.out.print("Start");
        new MainWindow().go();
    }
}
