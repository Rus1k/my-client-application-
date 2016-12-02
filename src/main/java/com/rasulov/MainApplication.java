package com.rasulov;


import com.rasulov.swing.LoginPanel;

import javax.swing.*;
import java.io.IOException;

public class MainApplication {

    public static final String URL = "http://localhost:8080/user";
    public static JFrame frame;
    public static JPanel panel;

    public static void main(String[] args) throws IOException, InterruptedException {

        LoginPanel loginPanel = new LoginPanel();

        frame = new JFrame("Java application");
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        frame.add(panel);


        loginPanel.getLoginPanel(panel);
        MainApplication.frame.repaint();

    }
}
