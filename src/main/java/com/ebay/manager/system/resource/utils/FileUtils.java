package com.ebay.manager.system.resource.utils;

import java.io.*;

public class FileUtils {

    public static boolean OverwriteFile(String file, String content){
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, false);
            writer.write(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isExist(String file){
        File f = new File(file);
        return f.exists();
    }

    public static String readFile(String file){
        FileReader fileReader = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
}
