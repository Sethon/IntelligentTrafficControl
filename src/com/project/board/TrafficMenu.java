package com.project.board;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.project.base.Controller;
import com.project.map.NagelMap;
import com.project.model.Car;
import com.project.model.Lane;

public class TrafficMenu extends JPanel implements ActionListener {

	private static final long serialVersionUID = -3952749405048123722L;

	
	private JSlider Density;
	private JSlider Speed;
	private JComboBox<String> combo;
	private JLabel label;
	private JLabel labelDensity;
	
	private JButton ComplexMap;
	private JButton ExperimentalMap;
	private JButton exit;
	private JPanel panel1;
	private JButton but;
	private JButton plus;
	private JButton minus;
	private JButton p;
	private JButton p2;
	private JComboBox<String> table;
	
	int i=0;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JFormattedTextField text;
	private JFormattedTextField text2;
//	private JComponent d;
	
	
	private Controller controller;
	protected boolean temp=false;
	
	public static final String NAGEL_MODEL = "Nagel's Model";
	public static final String ECA_MODEL ="ECA Model";
	public static final String LANE_CHANGING_MODEL = "Nagel's Model + Lane Changing";

	public static final String ZERO = "0";
	public static final String FIRST = "1-20";
	public static final String SECOND = "20-40";
	public static final String THIRD = "40-60";
	public static final String FOURTH = "60-80";
	public static final String FIFTH = "80-100";
	public static final String SIXTH = "100-120";
	
	
	
	public TrafficMenu(Controller controller){
		this.controller = controller;
		this.init();
		
	}
	

	private void init(){
		
		final Font buttonFont = new Font("Futura", 0, 16);
		final Dimension buttonDimension = new Dimension(200, 40);
		 label2 = new JLabel("1-20");
		 label2.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		 label3 = new JLabel("Ticks");
		 label3.setAlignmentX(JLabel.LEFT_ALIGNMENT); 
		 label4 = new JLabel(""+i);
		 text = new JFormattedTextField();
		 text2= new JFormattedTextField();
		// text.setMaximumSize(getMaximumSize());
		// text.setMinimumSize(getMaximumSize());
		// d= new JComponent();		 
		 
		 
		 text.setAlignmentX(Component.CENTER_ALIGNMENT);
		 plus = new JButton("+");
		 plus.setContentAreaFilled(false);
			plus.setAlignmentX(JButton.RIGHT_ALIGNMENT);
			plus.setFont(buttonFont);
			plus.setFocusPainted(false);
		//	plus.setPreferredSize(buttonDimension);
			plus.setSize(50,40);
			
			 p = new JButton("p");
			 p.setContentAreaFilled(false);
			 p.setAlignmentX(JButton.RIGHT_ALIGNMENT);
			p.setFont(buttonFont);
				p.setFocusPainted(false);
				//p.setPreferredSize(buttonDimension);
				p.setSize(50,40);
				
				p2 = new JButton("+");
				 p2.setContentAreaFilled(false);
				 p2.setAlignmentX(JButton.RIGHT_ALIGNMENT);
				p2.setFont(buttonFont);
					p2.setFocusPainted(false);
					//p.setPreferredSize(buttonDimension);
					p2.setSize(50,40);
			
			minus = new JButton("-");
			 minus.setContentAreaFilled(false);
				minus.setAlignmentX(JButton.RIGHT_ALIGNMENT);
				minus.setFont(buttonFont);
				minus.setFocusPainted(false);
			//	minus.setPreferredSize(buttonDimension);
				minus.setSize(50,20);
			
		 
	
		label = new JLabel("Number of Cars in the system: ");
		labelDensity = new JLabel("Waiting time: ");
		label.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		labelDensity.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		
		combo = new JComboBox<String>();
		combo.addItem(NAGEL_MODEL);
		combo.addItem(ECA_MODEL);
		combo.addItem(LANE_CHANGING_MODEL);
		combo.setVisible(true);
	
		table = new JComboBox<String>();
		table.addItem(ZERO);
		table.addItem(FIRST);
		table.addItem(SECOND);
		table.addItem(THIRD);
		table.addItem(FOURTH);
		table.addItem(FIFTH);
		table.addItem(SIXTH);
		table.setSize(40,40);
		table.setVisible(true);
		
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
		//exit.setMinimumSize(buttonDimension);
		exit.setSize(100,40);
	//	System.out.println(buttonDimension);
		
		
	
		
		
		this.combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String)combo.getSelectedItem();
				if(item == ECA_MODEL){
					controller.showMenuPage(2);
				}
				if(item == NAGEL_MODEL){
					controller.showMenuPage(1);
				}
				repaint();
			}
		});
	
		this.minus.addActionListener(new ActionListener() {
            @Override
           
            public void actionPerformed(ActionEvent e) {
            	i--;
            	System.out.println(i);
			}
        });
		this.plus.addActionListener(new ActionListener() {
            @Override
           
            public void actionPerformed(ActionEvent e) {
            	i++;
            	System.out.println(i);
            	
            	
			}
        });
		this.p.addActionListener(new ActionListener() {
            @Override
           
            public void actionPerformed(ActionEvent e) {
            	if(text.getText().length() !=0){
            	String a= text.getText(); 
        		
        		StringBuffer sb = new StringBuffer();
        		sb.append(a);
        		System.out.println(a);     	
            	}
			}
        });
		this.p2.addActionListener(new ActionListener() {
            @Override
           
            public void actionPerformed(ActionEvent e) {
          
           
            //	System.out.println(i);
            	if(text2.getText().length() !=0){
            	String item = (String)table.getSelectedItem();	
            	if(item == FIRST){
					String a = text2.getText();
					double foo = Double.parseDouble(a);
					System.out.println("1"+foo);
					
				}
				if(item == SECOND){
					String a = text2.getText();
					double foo = Double.parseDouble(a);
					System.out.println("2"+foo);
				}
				if(item == THIRD){
					String a = text2.getText();
					
					double foo2 = Double.parseDouble(a);
					System.out.println("3__"+foo2);
				}
				if(item == FOURTH){
					String a = text2.getText();
					double foo = Double.parseDouble(a);
					System.out.println("4"+foo);
				}
				if(item == FIFTH){
					String a = text2.getText();
					double foo = Double.parseDouble(a);
					System.out.println("5"+foo);
				}
				if(item == SIXTH){
					String a = text2.getText();
					double foo = Double.parseDouble(a);
					System.out.println("6"+foo);
				}
            	}
			}
        });
		this.plus.addActionListener(new ActionListener() {
            @Override
           
            public void actionPerformed(ActionEvent e) {
            	i++;
            	System.out.println(i);
            	
            	
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
		Box box2 = Box.createHorizontalBox();
		Box box3 = Box.createHorizontalBox();
		Box box4 = Box.createHorizontalBox();
		Box box5 = Box.createHorizontalBox();
		
		
		
		
		
		//box5.add();
		
		box3.add(label3);
		box3.add(table);
		box3.add(text2);
		box3.add(p2);
		
		box.add(Box.createVerticalStrut(00));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(00));
		//box.add(box2);
		box.add(Box.createVerticalStrut(100));
		box.add(box3);
		box.add(box4);
		box.add(box5);
	//	box4.repaint();
	
		
		box.add(Box.createVerticalStrut(10));
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(100));
		box4.add(label);
		box4.add(label4);
		box5.add(labelDensity);
		
		
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}