package com.rasulov.swing;

import com.rasulov.MainApplication;
import com.rasulov.swing.listener.BackButtonActionListener;
import com.rasulov.swing.listener.DeleteButtonActionListener;
import com.rasulov.swing.listener.SaveButtonActionListener;

import javax.swing.*;
import java.awt.*;

public class InfoPanel {
    public static JPanel getInfoPanel(JPanel panel) {

        JLabel labelGeneralText = new JLabel("USER INFO");
        JLabel labelName = new JLabel("Name:");
        JLabel labelLastName = new JLabel("Last name:");
        JLabel labelEmail = new JLabel("Email:");
        JLabel labelAge = new JLabel("Age:");
        JLabel labelHobby = new JLabel("Hobby");
        JLabel resultStatus = new JLabel();
        JTextField textFieldName = new JTextField();
        JTextField textFieldLastName = new JTextField();
        JTextField textFieldEmail = new JTextField();
        JTextField textFieldAge = new JTextField();
        JTextField textFieldHobby = new JTextField();
        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");
        JButton deleteButton = new JButton("Delete");

        labelGeneralText.setFont(new Font("Serif", Font.BOLD, 20));
        labelGeneralText.setForeground(Color.blue);
        panel.setLayout(null);

        labelGeneralText.setBounds(190, 30, 400, 30);
        labelName.setBounds(80, 70, 200, 30);
        labelLastName.setBounds(80, 110, 200, 30);
        labelEmail.setBounds(80, 150, 200, 30);
        labelAge.setBounds(80, 190, 200, 30);
        labelHobby.setBounds(80, 230, 200, 30);
        textFieldName.setBounds(200, 70, 200, 30);
        textFieldLastName.setBounds(200, 110, 200, 30);
        textFieldEmail.setBounds(200, 150, 200, 30);
        textFieldAge.setBounds(200, 190, 200, 30);
        textFieldHobby.setBounds(200, 230, 200, 30);
        saveButton.setBounds(80, 280, 100, 30);
        backButton.setBounds(200, 280, 100, 30);
        resultStatus.setBounds(80, 320, 200, 30);
        deleteButton.setBounds(320,280, 100, 30);


        panel.add(labelGeneralText);  //index in panel = 0
        panel.add(labelName);         //1
        panel.add(textFieldName);     //2
        panel.add(labelLastName);     //3
        panel.add(textFieldLastName); //4
        panel.add(labelEmail);        //5
        panel.add(textFieldEmail);    //6
        panel.add(labelAge);          //7
        panel.add(textFieldAge);      //8
        panel.add(labelHobby);        //9
        panel.add(textFieldHobby);    //10
        panel.add(saveButton);        //11
        panel.add(backButton);        //12
        panel.add(resultStatus);      //13
        panel.add(deleteButton);      //14

        SaveButtonActionListener infoListener = new SaveButtonActionListener();
        saveButton.addActionListener(infoListener);

        BackButtonActionListener backButtonListener = new BackButtonActionListener();
        backButton.addActionListener(backButtonListener);

        DeleteButtonActionListener deleteListener = new DeleteButtonActionListener();
        deleteButton.addActionListener(deleteListener);

        MainApplication.frame.repaint();

        return panel;
    }
}
