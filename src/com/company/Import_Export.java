package com.company;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface Import_Export {
    public void Export(String fileName) throws FileNotFoundException, UnsupportedEncodingException;
    public void Import(String path) throws FileNotFoundException, UnsupportedEncodingException;
}
