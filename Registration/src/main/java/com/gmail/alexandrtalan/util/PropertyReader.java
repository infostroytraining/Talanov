package com.gmail.alexandrtalan.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public static Properties getInstance(String path) throws FileNotFoundException {
        Properties property = new Properties();
        try (FileInputStream inputStream = new FileInputStream(path)) {
            property.load(inputStream);
            return property;
        } catch (IOException e) {
            throw new FileNotFoundException("ERROR: Properties file does not exist!");
        }
    }
}
