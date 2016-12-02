package com.rasulov.validator;

import lombok.extern.java.Log;

import java.io.*;

@Log
public class StreamToString {

    public static String convertStreamToString(InputStream inputStream) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            log.info("IOException :" + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.info("IOException :" + e);
            }
        }
        return stringBuilder.toString();
    }

}
