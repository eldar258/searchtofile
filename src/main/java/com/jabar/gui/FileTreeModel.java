package com.jabar.gui;

import com.jabar.search.FileThatReturnsName;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.io.FileFilter;

/**
 * Class FileTreeModel.
 *
 * @author edzabarov
 * @since 30.09.2017
 */
public class FileTreeModel implements TreeModel {
    /**
     * root.
     */
    private File root;
    /**
     * filter files.
     */
    private FileFilter filter;

    /**
     * constructor.
     * @param root -
     * @param filter -
     */
    public FileTreeModel(File root, FileFilter filter) {
        this.root = root;
        this.filter = filter;
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        File[] children = ((File) parent).listFiles(filter);
        return (children == null || index >= children.length) ? null : new FileThatReturnsName(((File) parent).getPath(), children[index].getName());
    }

    @Override
    public int getChildCount(Object parent) {
        File[] children = ((File) parent).listFiles(filter);
        return children == null ? 0 : children.length;
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((File) node).isFile();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        File[] children = ((File) parent).listFiles(filter);
        if (children == null) return -1;
        String childName = ((File) child).getName();
        for (int i = 0; i < children.length; i++) {
            if (childName.equals(children[i].getName())) return i;
        }
        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }
}
