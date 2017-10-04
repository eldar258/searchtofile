package com.jabar.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Class MainMenu.
 *
 * @author edzabarov
 * @since 28.09.2017
 */
public class MainMenu {
    /**
     * Main component.
     */
    private JPanel mainMenu;
    /**
     * It is necessary to create a unique tab.
     */
    private static int index = 1;
    /**
     * tab bar.
     */
    private JTabbedPane tabbedPane;

    /**
     * Constructor.
     */
    public MainMenu() {
        mainMenu = new JPanel();
        mainMenu.setLayout(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();
            JMenu tabs = new JMenu("Tabs");
                JMenuItem newTabMenu = new JMenuItem("New Tab");
                newTabMenu.setMnemonic(KeyEvent.VK_N);
                newTabMenu.addActionListener(new NewTabMenuActionListener());
                JMenuItem deleteTabMenu = new JMenuItem("Delete the selected tab");
                deleteTabMenu.setMnemonic(KeyEvent.VK_D);
                deleteTabMenu.addActionListener(new DeleteTabMenuListener());
        tabs.add(newTabMenu);
        tabs.add(deleteTabMenu);
        menuBar.add(tabs);
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Tab " + index++, new Menu().getMenu());
        //JPanel menu = new Menu().getMenu();
        mainMenu.add(menuBar, BorderLayout.NORTH);
        mainMenu.add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * get mainMenu.
     * @return - mainMenu.
     */
    public JPanel getMainMenu() {
        return mainMenu;
    }

    /**
     * listener for an item to create a tab.
     */
    private class NewTabMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tabbedPane.addTab("Tab " + MainMenu.index++, new Menu().getMenu());
        }
    }

    /**
     * listener for the delete a tab.
     */
    private class DeleteTabMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tabbedPane.remove(tabbedPane.getSelectedIndex());
        }
    }
}
