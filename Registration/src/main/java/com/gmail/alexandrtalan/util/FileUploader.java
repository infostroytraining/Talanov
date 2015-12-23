package com.gmail.alexandrtalan.util;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class FileUploader {

    public static String upload(HttpServletRequest request, String pathToSave, String[] contentTypes) throws IOException {
        ServletFileUpload upload = new ServletFileUpload();
        try {
            FileItemIterator iterator = upload.getItemIterator(request);
            while (iterator.hasNext()) {
                FileItemStream file = iterator.next();
                if (!file.isFormField() && validateFileType(file, contentTypes)) {
                    final String filePath = pathToSave + File.separator + new Random().nextInt() + file.getName();
                    try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
                        int i;
                        while ((i = file.openStream().read()) != -1) {
                            outputStream.write(i);
                        }
                        return filePath;
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static boolean validateFileType(FileItemStream file, String[] contentTypes) {
        String type = file.getContentType();
        return Arrays.asList(contentTypes).stream().anyMatch(type::equals);
    }

}
