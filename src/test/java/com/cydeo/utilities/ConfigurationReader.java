package com.cydeo.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    // 1-create an object from Properties class
    private static Properties properties=new Properties();

    static {


        try {
            // 2-we need to open the file in java memory: FileInputStream
            FileInputStream file=new FileInputStream("configuration.properties");

            // 3-Load the properties object using FileInputStream
            properties.load(file);

            // close the file
            file.close();

        } catch (IOException e) {
            System.out.println("Failed to load properties file!");
            e.printStackTrace();
        }


    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

}
