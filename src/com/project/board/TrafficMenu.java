package com.project.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.project.base.Controller;

public class TrafficMenu extends JPanel{

	private static final long serialVersionUID = -3952749405048123722L;

	private JRadioButton lightsON;
	private JRadioButton lightsOFF;
	private JSlider waitingTime;
	private JComboBox combo;
	private JLabel label;
	
	
	
	private JButton generateMap;
	private JButton exit;
	
	
	private Controller controller;
	protected boolean temp=false;
	
	
	public TrafficMenu(Controller controller){
		this.controller = controller;
		this.init();
		
	}
	
	
	private void init(){
		final Font buttonFont = new Font("Futura", 0, 16);
		final Dimension buttonDimension = new Dimension(200, 40);
		
		label = new JLabel("Red Lights");
		lightsON = new JRadioButton("ON");
		lightsON.setVisible(true);
		lightsOFF = new JRadioButton("OFF");
		lightsOFF.setVisible(true);

		
		combo = new JComboBox<String>();
		combo.addItem("Nagel's Model");
		combo.addItem("Random Model");
		combo.addItem("Group1's Model (lane changing)");
		combo.setVisible(true);
	
		
		waitingTime = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
		waitingTime.setMajorTickSpacing(5);
		waitingTime.setMinorTickSpacing(0);
		waitingTime.setPaintTicks(true);
		waitingTime.setPaintLabels(true);
		Hashtable<Integer, JLabel> waitingTimeTable = new Hashtable<Integer, JLabel>();
		waitingTimeTable.put(0, new JLabel("1/10s"));
		waitingTimeTable.put(50, new JLabel("5s"));
		waitingTime.setLabelTable(waitingTimeTable);
		
		
		generateMap = new JButton("Generate Map");
		generateMap.setContentAreaFilled(false);
		generateMap.setAlignmentX(JButton.CENTER_ALIGNMENT);
		generateMap.setFont(buttonFont);
		generateMap.setFocusPainted(false);
		generateMap.setPreferredSize(buttonDimension);
		generateMap.setMinimumSize(buttonDimension);
		generateMap.setMaximumSize(buttonDimension);
		
		exit = new JButton("Exit");
		exit.setContentAreaFilled(false);
		exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
		exit.setFont(buttonFont);
		exit.setFocusPainted(false);
		exit.setPreferredSize(buttonDimension);
		exit.setMinimumSize(buttonDimension);
		exit.setMaximumSize(buttonDimension);
		
		
		
		this.lightsON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toggleShowRedLight();
			}
		});
		
		this.lightsOFF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		this.combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.waitingTime.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               controller.getCurrentMap().setGlobalTrafficLightSwitchInterval(waitingTime.getValue());
            }
        });
		
		this.generateMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showMenuPage(0);
			}
		});
	
		
		Box box = Box.createVerticalBox();
		
		
		box.add(Box.createVerticalStrut(00));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(100));
		box.add(label);
		box.add(lightsON);
		box.add(lightsOFF);
		
		
		box.add(Box.createVerticalStrut(20));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(100));
		box.add(combo);
		
		box.add(waitingTime);
		box.add(Box.createVerticalStrut(10));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(100));
		
		box.add(generateMap);
		box.add(Box.createVerticalStrut(50));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(50));
		
		box.add(exit);
		box.add(Box.createVerticalStrut(50));
	
		this.add(box);	
	};
	
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Point2D center = new Point2D.Float(this.getWidth() / 2, this.getHeight() / 2);
		float radius = this.getWidth() / 2;
		float[] dist = { 
				0.6f, 0.8f 
		};
		Color[] colors = { 
				new Color(100, 100, 100), 
				new Color(100, 100, 100) 
		};	
		RadialGradientPaint gp = new RadialGradientPaint(center, radius, dist, colors);
		g2.setPaint(gp);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
