package com.rasulov.swing.listener;


import com.rasulov.MainApplication;
import com.rasulov.swing.InfoPanel;
import lombok.extern.java.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Log
public class RegistrationActionListener implements ActionListener{
    private JPanel mainPanel;


    public RegistrationActionListener(){
        log.info("registration listener constructor");
        mainPanel = MainApplication.panel;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        log.info("registration actionPerformed");
        JTextField emailField = (JTextField) mainPanel.getComponent(2);
        JButton registeredButton = new JButton("Registered");
        String email = emailField.getText();
        registeredButton.setBounds(80, 280, 100, 30);


        mainPanel.removeAll();

        mainPanel = InfoPanel.getInfoPanel(mainPanel);
        emailField = (JTextField) mainPanel.getComponent(6);
        emailField.setEditable(false);
        emailField.setText(email);

        mainPanel.add(registeredButton, 11);

        JButton deleteButton = (JButton) mainPanel.getComponent(15);
        deleteButton.setVisible(false);

        RegisteredActionListener registeredListener = new RegisteredActionListener(email);
        registeredButton.addActionListener(registeredListener);

    }
}
