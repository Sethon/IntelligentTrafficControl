package com.project.visuals.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.project.base.Controller;

public class MenuPage extends JPanel {

	private static final long serialVersionUID = -5614885626543441412L;
	public static Font buttonFont = new Font("Segoe UI", 0, 32);
	public static Dimension buttonDimension = new Dimension(400, 80);
	private JButton trafficControl, exit;
	private JLabel title;
	private Controller controller;

	public MenuPage(Controller controller) {
		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		final Font buttonFont = new Font("Futura", 0, 32);
		final Dimension buttonDimension = new Dimension(400, 80);

		this.title = new JLabel("Intelligent Traffic Control");
		this.title.setOpaque(false);
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setFont(new Font("Futura", 1, 40));
		this.title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		this.trafficControl = new JButton("Start Traffic Control");
		this.trafficControl.setContentAreaFilled(false);
		this.trafficControl.setAlignmentX(JButton.CENTER_ALIGNMENT);
		this.trafficControl.setFont(buttonFont);
		this.trafficControl.setFocusPainted(false);
		this.trafficControl.setPreferredSize(buttonDimension);
		this.trafficControl.setMinimumSize(buttonDimension);
		this.trafficControl.setMaximumSize(buttonDimension);

		this.exit = new JButton("Quit");
		this.exit.setContentAreaFilled(false);
		this.exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
		this.exit.setFont(buttonFont);
		this.exit.setFocusPainted(false);
		this.exit.setPreferredSize(buttonDimension);
		this.exit.setMinimumSize(buttonDimension);
		this.exit.setMaximumSize(buttonDimension);

		this.trafficControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showMenuPage(1);
			}
		});
		this.exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		Box innerBox = Box.createHorizontalBox();
		innerBox.add(this.trafficControl);
		innerBox.add(Box.createHorizontalStrut(50));
		innerBox.add(Box.createVerticalGlue());
		innerBox.add(this.exit);
		
		Box outerBox = Box.createVerticalBox();
		outerBox.add(Box.createVerticalStrut(75));
		outerBox.add(title);		
		outerBox.add(Box.createVerticalGlue());		
		outerBox.add(innerBox);
		outerBox.add(Box.createVerticalStrut(200));
		
		this.add(outerBox);
	}

	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Point2D center = new Point2D.Float(this.getWidth() / 2, this.getHeight() / 2);
		float radius = this.getWidth() / 2;
		float[] dist = { 
				0.6f, 0.8f 
		};
		Color[] colors = { 
				new Color(180, 180, 180), new Color(160, 160, 160) 
		};
		RadialGradientPaint gp = new RadialGradientPaint(center, radius, dist, colors);
		g2.setPaint(gp);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
