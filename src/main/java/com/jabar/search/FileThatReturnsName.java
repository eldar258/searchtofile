package com.jabar.search;

import java.io.File;

/**
 * Class FileThatReturnsName.
 * toString() returns file name.
 *
 * @author edzabarov
 * @since 30.09.2017
 */
public class FileThatReturnsName extends File {
    /**
     * constructor.
     * @param parent -
     * @param child -
     */
    public FileThatReturnsName(String parent, String child) {
        super(parent, child);
    }

    public FileThatReturnsName(String pathname) {
        super(pathname);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
