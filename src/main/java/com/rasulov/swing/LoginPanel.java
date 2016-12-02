package com.rasulov.swing;


import com.rasulov.swing.listener.LoginActionListener;
import com.rasulov.swing.listener.RegistrationActionListener;

import javax.swing.*;
import java.awt.*;

public class LoginPanel {


    public static JPanel getLoginPanel(JPanel panel) {

        JLabel labelGeneralText = new JLabel("LOGIN FORM");
        JLabel emailLabel = new JLabel("Email");
        JLabel statusLabel = new JLabel();
        JTextField emailField = new JTextField("");
        JButton loginButton = new JButton("Login");
        JButton registrationButton = new JButton("Registration");
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registrationButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        labelGeneralText.setForeground(Color.blue);
        labelGeneralText.setFont(new Font("Serif", Font.BOLD, 20));
        panel.setLayout(null);

        labelGeneralText.setBounds(180, 30, 400, 30);
        emailLabel.setBounds(80, 70, 200, 30);
        loginButton.setBounds(80, 110, 100, 30);
        emailField.setBounds(200, 70, 200, 30);
        statusLabel.setBounds(80, 150, 150, 30);
        registrationButton.setBounds(250, 150, 150, 30);
        registrationButton.setVisible(false);

        panel.add(labelGeneralText);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(loginButton);
        panel.add(statusLabel);
        panel.add(registrationButton);



        LoginActionListener loginActionListener = new LoginActionListener(panel);
        loginButton.addActionListener(loginActionListener);


        return panel;

    }

}
