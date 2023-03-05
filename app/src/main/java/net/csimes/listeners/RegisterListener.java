package net.csimes.listeners;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.event.*;

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


public class RegisterListener implements DocumentListener, ActionListener {
	private RegisterPage rp;
	
	public RegisterListener(RegisterPage rp) {
		this.rp = rp;
	}
	
	public void actionPerformed(ActionEvent e) {
		JTextField txt = (JTextField) e.getSource();
		
		if (txt.getName().equals("usernamefield")) {
			if (!txt.getText().equals("")) {
				txt.transferFocus();
			}
		} else {
			JTextField usrF = (JTextField) rp.components.get("passfield");
			JPasswordField passF = (JPasswordField) e.getSource();
			
			if (!String.valueOf(passF.getPassword()).equals("")) {
				this.rp.root.clean();
				this.rp.root.setVisible(false);
				
				Account acc = new SecurityControl(new Account().setUserName(usrF.getText()).setPasswd(String.valueOf(passF.getPassword())).setAccountType(1)).encryptAccount();
			
				File ownerAcc = new File(Initialize.rootAccPath + File.separator + acc.getUserName());
				
				try {
				ownerAcc.createNewFile();
				} catch (IOException ex) {
					ex.printStackTrace();
				};
				
				String path = Initialize.rootAccPath;
				WriteAccount.write(acc, path);

				String msg_ = "Registration completed. Proceed to Login?";
				int stat_ = JOptionPane.showOptionDialog(null,
							msg_, 
							"CSIMES - Registration Finished", 
							JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE,
							new ImageIcon(ImageControl.resizeImage(new ImageIcon(ResourceControl.getResourceFile("icons/csimes_full_bg.png")).getImage(), 35, 35)),
							new String[]{"Yes", "Exit"},
							"Yes"
							);

				if (stat_ == 0) {
					LoginPage lp = new LoginPage(Initialize.pages.get("credentials"));
					this.rp.root.setVisible(true);
					this.rp.root.clean();
					lp.paints();
					lp.page.repaint();
				} else {
					System.exit(0);
				}
				
				
			}
		}   
	}
	
	public void insertUpdate(DocumentEvent e) {
		
	}
	
	public void removeUpdate(DocumentEvent e) {
		
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		
	}
	
}
