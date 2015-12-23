package com.gmail.alexandrtalan.util;

import java.io.*;
import java.nio.charset.Charset;

public class MemoryForLog {

    private static final String FILE_PATH = "C:\\Users\\alexa\\Desktop\\Talanov\\LogsServer\\src\\main\\resources\\allLogs.txt";

    public static String getAllLogs() throws IOException {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH), Charset.forName("UTF-8")))){
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append('\n');
            }
            return builder.toString();
        }
    }

    public static void setLogs(String logs) throws IOException {
        StringBuilder builder = new StringBuilder();
        String allLogs = getAllLogs();
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), Charset.forName("UTF-8")))){
            builder.append(allLogs);
            builder.append(logs);
            writer.write(builder.toString());
            writer.flush();
        }
    }

}
