/*
 * ##################################################
 * #                                                #
 * # webViewConfigurer                              #
 * # Creates wvapp-Files for pfolta/WebView         #
 * #                                                #
 * # Copyright (C) by René Knipschild               #
 * #                                                #
 * # rk@reneknipschild.net                          #
 * # www.reneknipschild.net                         #
 * #                                                #
 * ##################################################
 *
 * File: Main.java - core
 * Version: 1.0.0
 * Last modified: 2016/10/17 16:30 GMT+1
 * Author: Alexander Eifler
 *
 * ===Notes==========================================
 * There are currently no notes
 * ==================================================
 */

package com.rkcsd.apps.clienttools.webviewconfigurator.core;

import com.rkcsd.apps.clienttools.webviewconfigurator.gui.Gui;

import javax.swing.*;

class Main {
    public static void main(String[] args){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        new Gui();
    }
}
