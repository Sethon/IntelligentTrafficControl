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
import java.util.Hashtable;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.project.base.Controller;

public class TrafficMenu extends JPanel{

	private JButton generateMap;
	private JButton exit;
	private Controller controller;
	private JSlider speed;
	private JSlider scale;
	private JSlider numberOfCars;
	
	public TrafficMenu(Controller controller){
		this.controller = controller;
		this.init();
		
	}
	
	
	private void init(){
		final Font buttonFont = new Font("Segoe UI", 0, 16);
		final Dimension buttonDimension = new Dimension(200, 40);
		
		speed = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
		speed.setMajorTickSpacing(20);		
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		Hashtable<Integer, JLabel> speedTable = new Hashtable<Integer, JLabel>();
		speedTable.put(0, new JLabel("Slow") );
		speedTable.put(0, new JLabel("Speed") );
		speedTable.put(100, new JLabel("Fast") );
		//speed.setBorder(BorderFactory.createTitledBorder("JSlider without Tick Marks"));
		//JLabel sliderLabel = new JLabel("Frames Per Second: ", JLabel.CENTER);
	   // sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		speed.setLabelTable(speedTable);
		
		
		scale = new JSlider(JSlider.HORIZONTAL, 0, 2, 1);
		scale.setMajorTickSpacing(1);
		scale.setMinorTickSpacing(0);
		scale.setPaintTicks(true);
		scale.setPaintLabels(true);
		Hashtable<Integer, JLabel> scaleTable = new Hashtable<Integer, JLabel>();
		scaleTable.put(0, new JLabel("Zoom Out"));
		scaleTable.put(2, new JLabel("Zoom In"));
		scale.setLabelTable(scaleTable);
		
		numberOfCars = new JSlider(JSlider.HORIZONTAL, 0, 200, 25);
		numberOfCars.setMajorTickSpacing(20);
		numberOfCars.setPaintTicks(true);
		numberOfCars.setPaintLabels(true);
		Hashtable<Integer, JLabel> carTable = new Hashtable<Integer, JLabel>();
		carTable.put(0, new JLabel("0"));
		carTable.put(200, new JLabel("200"));
		numberOfCars.setLabelTable(carTable);
		
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
		
		this.generateMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		this.exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showMenuPage(0);
			}
		});
	
		this.speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               
            }
        });
		
		this.scale.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               
            }
        });
		
		this.numberOfCars.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               
            }
        });
		
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(75));
		
		box.add(numberOfCars);
		box.add(Box.createVerticalStrut(5));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(100));
		box.add(speed);
		box.add(Box.createVerticalStrut(5));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(100));
		box.add(scale);
		box.add(Box.createVerticalStrut(5));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(100));
		box.add(generateMap);
		box.add(Box.createVerticalStrut(5));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(50));
		box.add(exit);
		box.add(Box.createVerticalStrut(125));

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
				new Color(160, 160, 160), 
				new Color(160, 160, 160) 
		};
		
		RadialGradientPaint gp = new RadialGradientPaint(center, radius, dist, colors);
		g2.setPaint(gp);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	}


}
