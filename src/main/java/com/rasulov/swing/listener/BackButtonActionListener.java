package com.rasulov.swing.listener;


import com.rasulov.MainApplication;
import com.rasulov.swing.LoginPanel;
import lombok.extern.java.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Log
public class BackButtonActionListener implements ActionListener {

    private JPanel mainPanel;

    public BackButtonActionListener(){
        log.info("Back action listener constructor");
        mainPanel = MainApplication.panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        log.info("Back action performed");

        mainPanel.removeAll();
        mainPanel = LoginPanel.getLoginPanel(mainPanel);
        MainApplication.frame.repaint();
    }
}
