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
import com.project.map.NagelMap;
import com.project.model.Car;
import com.project.model.Lane;

public class TrafficMenu extends JPanel{

	private static final long serialVersionUID = -3952749405048123722L;

	
	private JSlider Density;
	private JSlider Speed;
	private JComboBox combo;
	private JLabel label;
	private JLabel labelDensity;
	
	
	
	private JButton ComplexMap;
	private JButton ExperimentalMap;
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
		
		label = new JLabel("Number of Cars in the system: ");
		labelDensity = new JLabel("Waiting time: ");

		
		combo = new JComboBox<String>();
		combo.addItem("Nagel's Model");
		combo.addItem("Random Model");
		combo.addItem("Lane Changing Model");
		combo.setVisible(true);
	
		
		Density = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
		Density.setMajorTickSpacing(5);
		Density.setMinorTickSpacing(0);
		Density.setPaintTicks(true);
		Density.setPaintLabels(true);
		Hashtable<Integer, JLabel> DensityTable = new Hashtable<Integer, JLabel>();
		DensityTable.put(0, new JLabel("0"));
		DensityTable.put(50, new JLabel("200"));
		Density.setLabelTable(DensityTable);
		
		
		Speed = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
		Speed.setMajorTickSpacing(5);
		Speed.setMinorTickSpacing(0);
		Speed.setPaintTicks(true);
		Speed.setPaintLabels(true);
		Hashtable<Integer, JLabel> SpeedTable = new Hashtable<Integer, JLabel>();
		SpeedTable.put(0, new JLabel("0"));
		SpeedTable.put(50, new JLabel("150 Km/h"));
		Speed.setLabelTable(SpeedTable);
		
		
		ComplexMap = new JButton("Complex Map");
		ComplexMap.setContentAreaFilled(false);
		ComplexMap.setAlignmentX(JButton.CENTER_ALIGNMENT);
		ComplexMap.setFont(buttonFont);
		ComplexMap.setFocusPainted(false);
		ComplexMap.setPreferredSize(buttonDimension);
		ComplexMap.setMinimumSize(buttonDimension);
		ComplexMap.setMaximumSize(buttonDimension);
		
		ExperimentalMap = new JButton("Experimental Map");
		ExperimentalMap .setContentAreaFilled(false);
		ExperimentalMap .setAlignmentX(JButton.CENTER_ALIGNMENT);
		ExperimentalMap .setFont(buttonFont);
		ExperimentalMap .setFocusPainted(false);
		ExperimentalMap .setPreferredSize(buttonDimension);
		ExperimentalMap .setMinimumSize(buttonDimension);
		ExperimentalMap .setMaximumSize(buttonDimension);
		
		exit = new JButton("Exit");
		exit.setContentAreaFilled(false);
		exit.setAlignmentX(JButton.CENTER_ALIGNMENT);
		exit.setFont(buttonFont);
		exit.setFocusPainted(false);
		exit.setPreferredSize(buttonDimension);
		exit.setMinimumSize(buttonDimension);
		exit.setMaximumSize(buttonDimension);
		
		
		

		
		this.combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.Density.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            }
        });
		
		this.ComplexMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.ExperimentalMap.addActionListener(new ActionListener() {
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
		box.add(Box.createVerticalStrut(00));
		box.add(label);
		box.add(labelDensity);
		
		
		box.add(Box.createVerticalStrut(20));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(100));
		box.add(combo);
		
		box.add(Density);
		box.add(Box.createVerticalStrut(00));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(20));
		box.add(Speed);
		box.add(Box.createVerticalStrut(00));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(20));
		
		box.add(ComplexMap);
		box.add(Box.createVerticalStrut(5));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(30));
		
		box.add(ExperimentalMap);
		box.add(Box.createVerticalStrut(5));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(30));
		
		box.add(exit);
		box.add(Box.createVerticalStrut(10));
	
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