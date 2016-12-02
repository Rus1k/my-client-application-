package com.rasulov.swing.listener;

import com.rasulov.MainApplication;
import lombok.extern.java.Log;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Log
public class RegisteredActionListener implements ActionListener {
    private String url;
    private JPanel mainPanel;

    private String name;
    private String lastName;
    private String email;
    private String age;
    private String hobby;

    public RegisteredActionListener(String email) {
        log.info("Registered action listener");
        url = MainApplication.URL;
        this.email = email;
        mainPanel = MainApplication.panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //               ----- doPost -----

        log.info("registered action performed");
        JTextField nameField = (JTextField) mainPanel.getComponent(2);
        JTextField lastNameField = (JTextField) mainPanel.getComponent(4);
        JTextField ageField = (JTextField) mainPanel.getComponent(8);
        JTextField hobbyField = (JTextField) mainPanel.getComponent(10);
        JButton submit = (JButton) mainPanel.getComponent(11);
        JButton deleteButton = (JButton) mainPanel.getComponent(15);
        submit.setVisible(false);
        deleteButton.setVisible(true);
        name = nameField.getText();
        lastName = lastNameField.getText();
        age = ageField.getText();
        hobby = hobbyField.getText();

        CloseableHttpClient client = HttpClientBuilder.create().build();

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("name", name));
        urlParameters.add(new BasicNameValuePair("lastName", lastName));
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("age", age));
        urlParameters.add(new BasicNameValuePair("hobby", hobby));

        log.info("URL parameters" + urlParameters);

        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(httpPost);
            JLabel statusLabel = (JLabel) mainPanel.getComponent(14);

            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                statusLabel.setText("User saved successfully");
            }else {
                statusLabel.setText("User are not saved");
            }

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        MainApplication.frame.repaint();
    }
}
