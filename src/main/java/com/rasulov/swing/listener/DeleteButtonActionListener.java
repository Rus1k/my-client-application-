package com.rasulov.swing.listener;

import com.rasulov.MainApplication;
import com.rasulov.swing.LoginPanel;
import lombok.extern.java.Log;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

@Log
public class DeleteButtonActionListener implements ActionListener{

    private JPanel mainPanel;


    public DeleteButtonActionListener(){
        log.info("Delete action listener constructor");
        mainPanel = MainApplication.panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //               ----- doDelete -----

        log.info("Delete action performed");
        JTextField emailField = (JTextField) mainPanel.getComponent(6);
        String email = emailField.getText();

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpDelete request = new HttpDelete("http://localhost:8080/user?email="+email);
        HttpResponse response = null;

        mainPanel.removeAll();
        mainPanel = LoginPanel.getLoginPanel(mainPanel);
        JLabel statusLabel = (JLabel) mainPanel.getComponentAt(80, 150);

        try {
            response = client.execute(request);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
            statusLabel.setText("Successfully deleted!");
        }
        MainApplication.frame.repaint();
    }
}
