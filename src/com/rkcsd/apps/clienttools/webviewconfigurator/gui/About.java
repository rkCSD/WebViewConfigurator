/*
 * ##################################################
 * #                                                #
 * # WebViewConfigurator                            #
 * #                   								#
 * #                                                #
 * # Copyright (C) by rkCSD Eu                      #
 * #                                                #
 * #	                           email@rkcsd.com  #
 * #              www.customsoftwaredevelopment.de  #
 * #                                                #
 * ##################################################
 *
 * File: About.java - gui
 * Version: 1.0.0
 * Timestamp: 2016/09/27 15:56 CEST
 * Authors: Alexander Eifler, René Knipschild, Mahatma Gandhi
 *
 * ===Notes==========================================
 * There are currently no notes.
 * ==================================================
 */

package com.rkcsd.apps.clienttools.webviewconfigurator.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class About extends JDialog{

    About(JFrame parent){
        super(parent);
        setSize(300,280);
        setPreferredSize(new Dimension(400,270));
        setResizable(false);
        setLayout(null);
        setModal(true);
        setAlwaysOnTop(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        pack();
        setLocationRelativeTo(parent);

        Image image = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/rkcsd/apps/clienttools/webviewconfigurator/resources/rkCSD-Logo.png"));
        JLabel logo = new JLabel(new ImageIcon(image));
        logo.setBounds(90,10,222,60);
        getContentPane().add(logo);

        JLabel info1 = new JLabel("<html><b>WebViewConfigurator - v1.1</b></html>", SwingConstants.CENTER);
        info1.setBounds(5,80,390,50);
        getContentPane().add(info1);
        JLabel info2 = new JLabel("\u00a9 2016 René Knipschild - Custom Software Development", SwingConstants.CENTER);
        info2.setBounds(5,130,390,20);
        getContentPane().add(info2);
        JLabel info3 = new JLabel("<html><span style=\"color:blue;text-decoration:underline\">www.rkcsd.de</span></html>", SwingConstants.CENTER);
        info3.setBounds(5,160,390,20);
        info3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        info3.addMouseListener(new WebsiteMouseLister());
        getContentPane().add(info3);

        JButton btnSupport = new JButton("Support");
        btnSupport.setBounds(95,190,100,30);
        btnSupport.addMouseListener(new SupportMouseLister());
        getContentPane().add(btnSupport);

        JButton btnOK = new JButton("Schließen");
        btnOK.setBounds(205,190,100,30);
        btnOK.addActionListener(new OKActionListener());
        getContentPane().add(btnOK);

        setVisible(true);
    }

    private void close(){
        this.dispose();
    }

    private class OKActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            close();
        }
    }

    private class WebsiteMouseLister implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.reneknipschild.net"));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }

    private class SupportMouseLister implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            try {
                Desktop.getDesktop().browse(new URI("http://go.reneknipschild.net/quicksupport"));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }
}
