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
 * File: Gui.java - gui
 * Version: 1.1.0
 * Last modified: 2016/10/17 16:30 GMT+1
 * Author: Alexander Eifler, Kaspar Anand Bechtold
 *
 * ===Notes==========================================
 * There are currently no notes
 * ==================================================
 */

package com.rkcsd.apps.clienttools.webviewconfigurator.gui;

import sun.misc.BASE64Encoder;


import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.xml.parsers.*;

public class Gui extends JFrame{

    private JTextField txtTitle,txtUrl,txtProfileID,txtX,txtY,txtWidth,txtHeight;
    private JCheckBox chkUsePageTitle,chkJavascriptEnabled,chkOpenExternalLinks,chkBackEnabled,chkForwardEnabled,chkHomeEnabled,chkRefreshEnabled,chkMinimizable,chkMaximizable,chkResizable,chkMaximized;
    private JLabel lblIconImage;
    private Image imgIcon, imgLogo;
    private JMenuBar menubar;
    private JMenu jmenu,jmenuaction,jmenuinfo;
    private JMenuItem close, export, imporT,about;

    public Gui(){
        super("WebView-Configurator - by rkCSD \u00a9 2016 - v1.1");
        setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/rkcsd/apps/clienttools/webviewconfigurator/resources/rkIcon.png")));
        setSize(1000,580);
        setPreferredSize(new Dimension(600,750));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        menubar = new JMenuBar();
        jmenu = new JMenu("Datei");
        jmenuaction = new JMenu("Aktionen");
        jmenuinfo = new JMenu("Information");
        menubar.add(jmenu);
        menubar.add(jmenuaction);
        menubar.add(jmenuinfo);
        this.setJMenuBar(menubar);
        close = new JMenuItem("Programm schließen");
        close.addActionListener(new CloseButtonActionListener());
        jmenu.add(close);
        export = new JMenuItem("wvapp-Datei exportieren");
        export.addActionListener(new ExportButtonActionListener());
        jmenuaction.add(export);
        imporT = new JMenuItem("wvapp-Datei einlesen");
        imporT.addActionListener(new ReadwvappDateiActionListener());
        jmenuaction.add(imporT);
        about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
        						public void actionPerformed(ActionEvent e) {
        						new About(Gui.this);	
        						}
        });
        jmenuinfo.add(about);

        JPanel panelBasic = new JPanel(null);
        panelBasic.setBounds(5,5,580,280);
        panelBasic.setBorder(BorderFactory.createTitledBorder("Basiseinstellungen"));

        JLabel lblTitle = new JLabel("Titel:");
        lblTitle.setBounds(10,15,100,20);
        panelBasic.add(lblTitle);

        txtTitle = new JTextField();
        txtTitle.setBounds(10,35,380,30);
        panelBasic.add(txtTitle);

        chkUsePageTitle = new JCheckBox("Als Seitentitel benutzen?");
        chkUsePageTitle.setBounds(390,35,210,30);
        chkUsePageTitle.setSelected(true);
        panelBasic.add(chkUsePageTitle);

        JLabel lblUrl = new JLabel("URL:");
        lblUrl.setBounds(10,70,100,20);
        panelBasic.add(lblUrl);

        txtUrl = new JTextField("https://");
        txtUrl.setBounds(10,90,560,30);
        panelBasic.add(txtUrl);

        JLabel lblProfileID = new JLabel("Profil-ID:");
        lblProfileID.setBounds(10,125,100,20);
        panelBasic.add(lblProfileID);

        txtProfileID = new JTextField();
        txtProfileID.setBounds(10,145,560,30);
        panelBasic.add(txtProfileID);

        JLabel lblIcon = new JLabel("Icon:");
        lblIcon.setBounds(10,180,100,20);
        panelBasic.add(lblIcon);

        imgIcon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/rkcsd/apps/clienttools/webviewconfigurator/resources/rkIcon.png"));

        lblIconImage = new JLabel(new ImageIcon(shrinkImage(imgIcon)));
        lblIconImage.setBounds(15,200,64,64);
        panelBasic.add(lblIconImage);

		imgLogo = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/rkcsd/apps/clienttools/webviewconfigurator/resources/rkCSD-Logo_small.png"));
        JLabel lblLogoImg = new JLabel(new ImageIcon(imgLogo));
        lblLogoImg.setBounds(241,640,118,37);
        //lblLogoImg.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        getContentPane().add(lblLogoImg);
        
        JButton btnIcon = new JButton("Bild einfügen...");
        btnIcon.setBounds(90,217,130,30);
        btnIcon.addActionListener(new OpenImageActionListener());
        panelBasic.add(btnIcon);

        getContentPane().add(panelBasic);


        JPanel panelBrowser = new JPanel(null);
        panelBrowser.setBounds(5,290,580,55);
        panelBrowser.setBorder(BorderFactory.createTitledBorder("Browsereinstellungen"));

        chkJavascriptEnabled = new JCheckBox("Javascript aktiviert?");
        chkJavascriptEnabled.setBounds(10,20,300,20);
        chkJavascriptEnabled.setSelected(true);
        panelBrowser.add(chkJavascriptEnabled);

        chkOpenExternalLinks = new JCheckBox("Externe Links erlauben?");
        chkOpenExternalLinks.setBounds(300,20,300,20);
        panelBrowser.add(chkOpenExternalLinks);

        getContentPane().add(panelBrowser);


        JPanel panelKeyboard = new JPanel(null);
        panelKeyboard.setBounds(5,355,580,80);
        panelKeyboard.setBorder(BorderFactory.createTitledBorder("Tastaturkürzel"));

        chkBackEnabled = new JCheckBox("'Zurück' erlauben?");
        chkBackEnabled.setBounds(10,20,300,20);
        panelKeyboard.add(chkBackEnabled);

        chkForwardEnabled = new JCheckBox("'Vorwärts' erlauben?");
        chkForwardEnabled.setBounds(300,20,300,20);
        panelKeyboard.add(chkForwardEnabled);

        chkHomeEnabled = new JCheckBox("'Home' erlauben?");
        chkHomeEnabled.setBounds(10,45,300,20);
        panelKeyboard.add(chkHomeEnabled);

        chkRefreshEnabled = new JCheckBox("'Seite neu laden' erlauben?");
        chkRefreshEnabled.setBounds(300,45,300,20);
        panelKeyboard.add(chkRefreshEnabled);

        getContentPane().add(panelKeyboard);


        JPanel panelWindow = new JPanel(null);
        panelWindow.setBounds(5,445,580,190);
        panelWindow.setBorder(BorderFactory.createTitledBorder("Fenstereinstellungen"));

        JLabel lblPosition = new JLabel("Fenster-Startposition:");
        lblPosition.setBounds(10,15,300,20);
        panelWindow.add(lblPosition);

        JLabel lblX = new JLabel("X:");
        lblX.setBounds(10,35,20,30);
        panelWindow.add(lblX);

        txtX = new JTextField("0");
        txtX.setBounds(20,35,50,30);
        panelWindow.add(txtX);

        JLabel lblY = new JLabel("Y:");
        lblY.setBounds(80,35,20,30);
        panelWindow.add(lblY);

        txtY = new JTextField("0");
        txtY.setBounds(90,35,50,30);
        panelWindow.add(txtY);

        JLabel lblSize = new JLabel("Fenstergröße:");
        lblSize.setBounds(10,70,300,20);
        panelWindow.add(lblSize);

        txtWidth = new JTextField("1024");
        txtWidth.setBounds(10,90,50,30);
        panelWindow.add(txtWidth);

        JLabel lblTextX = new JLabel("x");
        lblTextX.setBounds(65,90,20,30);
        panelWindow.add(lblTextX);

        txtHeight = new JTextField("768");
        txtHeight.setBounds(80,90,50,30);
        panelWindow.add(txtHeight);

        JLabel lblTextPixel = new JLabel("Pixel");
        lblTextPixel.setBounds(135,90,50,30);
        panelWindow.add(lblTextPixel);

        chkMinimizable = new JCheckBox("Minimierbar?");
        chkMinimizable.setBounds(10,130,300,20);
        panelWindow.add(chkMinimizable);

        chkMaximizable = new JCheckBox("Maximierbar?");
        chkMaximizable.setBounds(300,130,300,20);
        panelWindow.add(chkMaximizable);

        chkResizable = new JCheckBox("Fenstergröße veränderbar?");
        chkResizable.setBounds(10,155,300,20);
        panelWindow.add(chkResizable);

        chkMaximized = new JCheckBox("Maximiert starten?");
        chkMaximized.setBounds(300,155,300,20);
        panelWindow.add(chkMaximized);

        getContentPane().add(panelWindow);

/*JButton btnExport = new JButton("wvapp-Datei exportieren");
        btnExport.setBounds(290,640,180,30);
        btnExport.addActionListener(new ExportButtonActionListener());
        getContentPane().add(btnExport);

        JButton btnClose = new JButton("Schließen");
        btnClose.setBounds(480,640,100,30);
        btnClose.addActionListener(new CloseButtonActionListener());
        getContentPane().add(btnClose);

        JButton btnReadwvapp = new JButton("wvapp-Datei einlesen");
        btnReadwvapp.setBounds(110,640,170,30);
        btnReadwvapp.addActionListener(new ReadwvappDateiActionListener());
        getContentPane().add(btnReadwvapp);*/

        setVisible(true);
    }

    private String chk(JCheckBox chkBox){
        return chkBox.isSelected()?"true":"false";
    }

    private String txt(JTextField txtField){
        return txtField.getText();
    }

    private String img(Image img){
        BufferedImage bimg = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimg.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String imgIconString = "";

        try {
            ImageIO.write(bimg,"png",bos);
            byte[] imageBytes = bos.toByteArray();
            BASE64Encoder enc = new BASE64Encoder();
            imgIconString = enc.encode(imageBytes);
            bos.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return imgIconString.replace("\n","");
    }

    private BufferedImage shrinkImage(Image img){
        BufferedImage imgIconResized = new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imgIconResized.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(img,0,0,64,64,null);
        g.dispose();
        return imgIconResized;
    }

    private class ExportButtonActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "\n" +
                    "<wvapp version=\"0.0.1\">\n" +
                    "    <title usePageTitle=\"" + chk(chkUsePageTitle) + "\">" + txt(txtTitle) + "</title>\n" +
                    "    <url>" + txt(txtUrl) + "</url>\n" +
                    "\n" +
                    "    <profileId>" + txt(txtProfileID) + "</profileId>\n" +
                    "\n" +
                    "    <icon>"+img(imgIcon)+"</icon>\n" +
                    "\n" +
                    "    <browser>\n" +
                    "        <javascriptEnabled>" + chk(chkJavascriptEnabled) + "</javascriptEnabled>\n" +
                    "        <openExternalLinks>" + chk(chkOpenExternalLinks) + "</openExternalLinks>\n" +
                    "    </browser>\n" +
                    "\n" +
                    "    <keyboard>\n" +
                    "        <backEnabled>" + chk(chkBackEnabled) + "</backEnabled>\n" +
                    "        <forwardEnabled>" + chk(chkForwardEnabled) + "</forwardEnabled>\n" +
                    "        <homeEnabled>" + chk(chkHomeEnabled) + "</homeEnabled>\n" +
                    "        <refreshEnabled>" + chk(chkRefreshEnabled) + "</refreshEnabled>\n" +
                    "    </keyboard>\n" +
                    "\n" +
                    "    <window>\n" +
                    "        <x>" + txt(txtX) + "</x>\n" +
                    "        <y>" + txt(txtY) + "</y>\n" +
                    "        <width>" + txt(txtWidth) + "</width>\n" +
                    "        <height>" + txt(txtHeight) + "</height>\n" +
                    "\n" +
                    "        <resizable>" + chk(chkResizable) + "</resizable>\n" +
                    "        <minimizable>" + chk(chkMinimizable) + "</minimizable>\n" +
                    "        <maximizable>" + chk(chkMaximizable) + "</maximizable>\n" +
                    "\n" +
                    "        <maximized>" + chk(chkMaximized) + "</maximized>\n" +
                    "    </window>\n" +
                    "</wvapp>";

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Wo soll die wvapp-Datei gespeichert werden?");
            fileChooser.setSelectedFile(new File(txt(txtProfileID)+".wvapp"));
            int userSelection = fileChooser.showSaveDialog(Gui.this);
            if(userSelection == JFileChooser.APPROVE_OPTION){
                try{
                File file = fileChooser.getSelectedFile();
                if(!file.exists())
                    file.createNewFile();

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(xml);
                bw.close();
                fw.close();
                }catch(IOException e1){
                    e1.printStackTrace();
                }
            }
        }
    }

    private class OpenImageActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            FileDialog fd = new FileDialog(Gui.this,"Wählen Sie eine Bilddatei aus", FileDialog.LOAD);
            fd.setVisible(true);
            String filename = fd.getDirectory() + fd.getFile();
            if(fd.getFile() != null){
                try {
                    imgIcon = ImageIO.read(new File(filename));
                    lblIconImage.setIcon(new ImageIcon(shrinkImage(imgIcon)));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private class ReadwvappDateiActionListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		FileDialog fd = new FileDialog(Gui.this,"Wählen SIe eine wvapp-Datei aus", FileDialog.LOAD);
    		fd.setVisible(true);
    		File f = null;
    		try {
    			f = new File(fd.getDirectory() + fd.getFile());
    			if (!f.exists()) {
    				return;
    			}
    		}
    		catch(Exception e1) {
    			e1.printStackTrace();
    		}
    		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    		DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();
				
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			}
			Document doc = null;
    		try {
				doc = db.parse(f);
			} catch (SAXException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		doc.getDocumentElement().normalize();
    		
    		txtWidth.setText(doc.getElementsByTagName("width").item(0).getTextContent());
    		txtHeight.setText(doc.getElementsByTagName("height").item(0).getTextContent());
    		txtUrl.setText(doc.getElementsByTagName("url").item(0).getTextContent());
    		txtProfileID.setText(doc.getElementsByTagName("profileId").item(0).getTextContent());
    		txtX.setText(doc.getElementsByTagName("x").item(0).getTextContent());
    		txtY.setText(doc.getElementsByTagName("y").item(0).getTextContent());
    		txtTitle.setText(doc.getElementsByTagName("title").item(0).getTextContent());
    	
    		if (((Element)doc.getElementsByTagName("title").item(0)).getAttribute("usePageTitle").equals("true")) {
    			chkUsePageTitle.setSelected(true);
    		}
    		else {
    			chkUsePageTitle.setSelected(false);	
    		}
    		if (doc.getElementsByTagName("javascriptEnabled").item(0).getTextContent().equals("true")) {
    			chkJavascriptEnabled.setSelected(true);
    		}
    		else {
    			chkJavascriptEnabled.setSelected(false);
    		}
    		if (doc.getElementsByTagName("openExternalLinks").item(0).getTextContent().equals("true")) {
    			chkOpenExternalLinks.setSelected(true);
    		}
    		else {
    			chkOpenExternalLinks.setSelected(false);
    		}
    		if (doc.getElementsByTagName("backEnabled").item(0).getTextContent().equals("true")) {
    			chkBackEnabled.setSelected(true);
    		}
    		else {
    			chkBackEnabled.setSelected(false);
    		}
    		if (doc.getElementsByTagName("forwardEnabled").item(0).getTextContent().equals("true")) {
    			chkForwardEnabled.setSelected(true);
    		}
    		else {
    			chkForwardEnabled.setSelected(false);
    		}
    		if (doc.getElementsByTagName("homeEnabled").item(0).getTextContent().equals("true")) {
    			chkHomeEnabled.setSelected(true);
    		}
    		else {
    			chkHomeEnabled.setSelected(false);
    		}
    		if (doc.getElementsByTagName("refreshEnabled").item(0).getTextContent().equals("true")) {
    			chkRefreshEnabled.setSelected(true);
    		}
    		else {
    			chkRefreshEnabled.setSelected(false);
    		}
    		if (doc.getElementsByTagName("resizable").item(0).getTextContent().equals("true")) {
    			chkResizable.setSelected(true);
    		}
    		else {
    			chkResizable.setSelected(false);
    		}
    		if (doc.getElementsByTagName("minimizable").item(0).getTextContent().equals("true")) {
    			chkMinimizable.setSelected(true);
    		}
    		else {
    			chkMinimizable.setSelected(false);
    		}
    		if (doc.getElementsByTagName("maximizable").item(0).getTextContent().equals("true")) {
    			chkMaximizable.setSelected(true);
    		}
    		else {
    			chkMaximizable.setSelected(false);
    		}
    		if (doc.getElementsByTagName("maximized").item(0).getTextContent().equals("true")) {
    			chkMaximized.setSelected(true);
    		}
    		else {
    			chkMaximized.setSelected(false);
    		}
    		
    		InputStream in = null;
    		try {
				in = new ByteArrayInputStream(Base64.decode(doc.getElementsByTagName("icon").item(0).getTextContent().getBytes()));
			} catch (Base64DecodingException e1) {
				e1.printStackTrace();
			} catch (DOMException e1) {
				e1.printStackTrace();
			}
    		try {
				Image img = ImageIO.read(in);
				   lblIconImage.setIcon(new ImageIcon(shrinkImage(img)));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	}
    }
    
    private class CloseButtonActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}
