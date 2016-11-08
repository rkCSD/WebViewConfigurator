package com.rkcsd.apps.clienttools.webviewconfigurator.gui;

/*
 * ##################################################
 * #                                                #
 * # rkNumberConverter                              #
 * # Number system conversation                     #
 * #                                                #
 * # Copyright (C) by René Knipschild               #
 * #                                                #
 * #                                email@rkcsd.com #
 * #                         www.reneknipschild.net #
 * #                                                #
 * ##################################################
 *
 * File: ComfortGridBagLayout.java - gui
 * Version: 1.0.0
 * Last modified: 2008/07/23 23:44 CEST
 * Author: rkCSD (go.reneknipschild.net/com)
 *
 * ===Notes==========================================
 * There are currently no notes.
 * ==================================================
 */



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;

public class ComfortGridBagLayout extends GridBagLayout {
	private static final long serialVersionUID = 1L;

	private GridBagConstraints constraints;

	public void createGridBagConstraints(JComponent component, int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill, double weightx, double weighty, int ipadx, int ipady) {
		this.constraints = new GridBagConstraints();
		this.constraints.gridx = gridx;
		this.constraints.gridy = gridy;
		this.constraints.gridwidth = gridwidth;
		this.constraints.gridheight = gridheight;
		this.constraints.anchor = anchor;
		this.constraints.fill = fill;
		this.constraints.weightx = weightx;
		this.constraints.weighty = weighty;
		this.constraints.insets = new Insets(4, 4, 4, 4);
		this.constraints.ipadx = ipadx;
		this.constraints.ipady = ipady;
		this.setConstraints(component, this.constraints);
	}
}