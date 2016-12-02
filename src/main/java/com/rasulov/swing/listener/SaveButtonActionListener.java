package com.rasulov.swing.listener;

import com.rasulov.MainApplication;
import lombok.extern.java.Log;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log
public class SaveButtonActionListener implements ActionListener {
    private JPanel panel;
    private String url;

    public SaveButtonActionListener() {
        log.info("save action listener constructor");
        panel = MainApplication.panel;
        url = MainApplication.URL;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //               ----- doPut -----

        log.info("Save action performed");

        JTextField name = (JTextField) panel.getComponent(2);
        JTextField lastName = (JTextField) panel.getComponent(4);
        JTextField email = (JTextField) panel.getComponent(6);
        JTextField age = (JTextField) panel.getComponent(8);
        JTextField hobby = (JTextField) panel.getComponent(10);
        JLabel statusLabel = (JLabel) panel.getComponentAt(80, 320);
        email.setEditable(false);

        CloseableHttpClient client = HttpClientBuilder.create().build();

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("name", name.getText()));
        urlParameters.add(new BasicNameValuePair("lastName", lastName.getText()));
        urlParameters.add(new BasicNameValuePair("email", email.getText()));
        urlParameters.add(new BasicNameValuePair("age", age.getText()));
        urlParameters.add(new BasicNameValuePair("hobby", hobby.getText()));

        log.info("URL parameters" + urlParameters);

        try {
            HttpPut httpPut = new HttpPut(url);
            httpPut.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(httpPut);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                statusLabel.setText("Changes saved successfully");
            }

            log.info("PUT Response status : " + response.getStatusLine().getStatusCode());

            client.execute(httpPut);

        } catch (IOException e) {
            log.info(e.toString());
        }
        MainApplication.frame.repaint();
    }
}
