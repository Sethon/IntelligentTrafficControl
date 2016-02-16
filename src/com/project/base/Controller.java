package com.project.base;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.project.board.BoardPane;

import com.project.visuals.frame.Frame;
import com.project.visuals.menu.MenuPage;



public class Controller {

	private ArrayList<JComponent> menuPages;

	private JFrame frame;
	//private GhostController ghostController2;

	public Controller() {

	}

	public void init() {
		this.frame = new Frame();

		

		this.menuPages = new ArrayList<JComponent>();

		this.initMenuPages();
		this.showMenuPage(0);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.pack();
		this.frame.setLocation((int) ((screenSize.getWidth() / 2) - (frame.getSize().getWidth() / 2)), (int) ((screenSize.getHeight() / 2) - (frame.getSize().getHeight() / 2)));
		this.frame.setVisible(true);
	}
	
	private void initMenuPages() {
		this.menuPages.add(new MenuPage(this));
		this.menuPages.add(new BoardPane(this));
		
	}

	public void showMenuPage(int index) {
		this.showPanel(this.menuPages.get(index));
	}

	public void showPanel(JComponent panel) {
		this.frame.setContentPane(panel);
		this.frame.pack();
	}

}
