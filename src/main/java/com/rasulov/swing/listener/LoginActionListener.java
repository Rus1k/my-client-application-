package com.rasulov.swing.listener;

import com.rasulov.MainApplication;
import com.rasulov.entity.User;
import com.rasulov.swing.InfoPanel;
import com.rasulov.validator.EmailValidator;
import com.rasulov.validator.StreamToString;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

@Log
@NoArgsConstructor
public class LoginActionListener implements ActionListener {
    EmailValidator emailValidator = new EmailValidator();
    JPanel panel;
    JPanel mainPanel;
    static String email;

    public LoginActionListener(JPanel jpanel) {
        mainPanel = MainApplication.panel;
        panel = jpanel;
    }

    public void actionPerformed(ActionEvent actionEvent) {

        //               ----- doGet -----

        CloseableHttpClient client = HttpClientBuilder.create().build();
        JTextField emailTextField = (JTextField) panel.getComponent(2);
        JLabel statusLabel = (JLabel) panel.getComponent(4);
        email = emailTextField.getText();

        if (!emailValidator.validate(email)) {
            log.info("not valid");
            statusLabel.setText("Email is not valid");
        } else {
            HttpGet httpGet = new HttpGet("http://localhost:8080/user?email=" + email);

            log.info("HttpGet = " + httpGet);

            try {
                HttpResponse httpResponse = client.execute(httpGet);
                InputStream inputStream = httpResponse.getEntity().getContent();
                String response = StreamToString.convertStreamToString(inputStream);

                log.info("Response: " + response);

                if (httpResponse.getStatusLine().getStatusCode() == (HttpStatus.SC_OK)) {

                    log.info("User found");

                    JSONObject json = new JSONObject(response);
                    User user = User.builder()
                            .name(json.getString("name"))
                            .lastName(json.getString("lastName"))
                            .email(json.getString("email"))
                            .age(json.getInt("age"))
                            .hobby(json.getString("hobby"))
                            .build();

                    log.info("User: " + user);

                    mainPanel.removeAll();
                    mainPanel = InfoPanel.getInfoPanel(panel);

                    JTextField name = (JTextField) panel.getComponent(2);
                    JTextField lastName = (JTextField) panel.getComponent(4);
                    JTextField userEmail = (JTextField) panel.getComponent(6);
                    JTextField age = (JTextField) panel.getComponent(8);
                    JTextField hobby = (JTextField) panel.getComponent(10);
                    userEmail.setEditable(false);

                    name.setText(user.getName());
                    lastName.setText(user.getLastName());
                    userEmail.setText(user.getEmail());
                    age.setText(Integer.toString(user.getAge()));
                    hobby.setText(user.getHobby());

                } else {
                    log.info("User not found");
                    statusLabel.setText("User not found! Click =>");
                    JButton registrationButton = (JButton) panel.getComponent(5);
                    registrationButton.setVisible(true);

                    RegistrationActionListener registrationActionListener = new RegistrationActionListener();
                    registrationButton.addActionListener(registrationActionListener);
                }

            } catch (IOException e) {
                log.info("IOException :" + e);
            }
        }
        MainApplication.frame.repaint();
    }
}
