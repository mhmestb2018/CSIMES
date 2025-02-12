package net.csimes.mouse;

import java.io.*;													 // Importing the java I/O package
import java.awt.*;										 	         // Importing the Abstract Window Toolkit package
import java.util.*;													 // Importing the java Utility package
import javax.swing.*;												 // Importing the java Swing package
import java.awt.image.*;
import java.awt.event.*;											 // Importing the AWT Event package
import javax.swing.text.*;											 // Importing the Text package from java Swing
import javax.swing.border.*;										 // Importing the Border package from java Swing

import net.csimes.io.*;
import net.csimes.img.*;
import net.csimes.sec.*;
import net.csimes.res.*;
import net.csimes.init.*;
import net.csimes.page.*;
import net.csimes.temp.*;
import net.csimes.util.*;
import net.csimes.splash.*;
import net.csimes.listeners.*;




public class MainMouse extends MouseAdapter {
	private JLabel label;
	private Page page;
	private String iconType;
	private JScrollPane table;
	private HashMap<String,Component> components;
	
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	public int rootWidth = (int) (((float) dimension.width) * 0.8);
	public int rootHeight = (int) (((float) dimension.height) * 0.8);
	
	public Sidebars mainPanel;
	public MAINPAGE mpp;
	
	public MainMouse(MAINPAGE mpp, Page page, Sidebars mainPanel, JLabel label, JScrollPane table, String iconType, HashMap<String,Component> components) {
		this.label = label;
		this.page = page;
		this.table = table;
		this.components = components;
		this.iconType = iconType;
		this.mainPanel = mainPanel;
		this.mpp = mpp;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (this.label.getName().equals("exits")) {
				Icon img = label.getIcon();
			int w = img.getIconWidth();
			int h = img.getIconHeight();
			label.setIcon(new ImageIcon(
				ImageControl.resizeImage(
					new ImageIcon(ResourceControl.getResourceFile("icons/" + this.iconType + "_selected.png")).getImage(),
				w, h
			)));
			
			WindowEvent we = new WindowEvent(this.page, WindowEvent.WINDOW_CLOSING);
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(we);
		} else if (this.label.getName().equals("hamburger")) {
			Sidebars panel = (Sidebars) this.components.get("sidebar");
			JScrollPane panes = (JScrollPane) this.components.get("tablespane");
			
			if (panel.isShown && mainPanel.isShown) {
				panel.runs();
				this.mpp.mainPanel.runs();
			} else {
				panel.runs();
				this.mpp.mainPanel.runs();
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if (this.label.getName().equals("exits")) {
			Icon img = label.getIcon();
			int w = img.getIconWidth();
			int h = img.getIconHeight();
			label.setIcon(new ImageIcon(
				ImageControl.resizeImage(
					new ImageIcon(ResourceControl.getResourceFile("icons/" + this.iconType + "_clicked.png")).getImage(),
				w, h
			)));
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if (this.label.getName().equals("exits")) {
			Icon img = label.getIcon();
			int w = img.getIconWidth();
			int h = img.getIconHeight();
			label.setIcon(new ImageIcon(
				ImageControl.resizeImage(
					new ImageIcon(ResourceControl.getResourceFile("icons/" + this.iconType + ".png")).getImage(),
				w, h
			)));
		}
	}
}
