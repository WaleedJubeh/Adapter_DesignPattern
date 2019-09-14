package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface Import_Export {
    public void ExportXML(String fileName) throws FileNotFoundException, UnsupportedEncodingException;
    public void ImportXML(String path) throws IOException;
}
