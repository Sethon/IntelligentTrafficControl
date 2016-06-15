package com.project.base;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.project.visuals.frame.SimulationFrame;
import com.project.board.BaseSimulationPanel;
import com.project.cellular.Map;
import com.project.map.NagelMap;
import com.project.map.TickListener;
import com.project.visuals.frame.Activatable;
import com.project.visuals.frame.Frame;
import com.project.visuals.frame.NagelSimulationFrame;
import com.project.visuals.menu.MenuPage;

/**
 * control everything that happens
 * 
 *
 */

public class Controller {

	private ArrayList<Activatable> menuPages;

	private JFrame frame;
	
	private Map currentMap;
	private NagelMap nagelMap;
	
	private boolean showLights = false;
	private int menuIndex = 0;
	private ArrayList<TickListener> tickListeners = new ArrayList<TickListener>();
	
	public Controller() {

	}
	//new frame
	public void init() {
		this.frame = new Frame();
		//screens in program
		this.menuPages = new ArrayList<Activatable>();
		//construct menu pages
		this.initMenuPages();
		//0 - index 0 with the array 
		//shows the menu page in the frame
		this.showMenuPage(0);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.pack();
		this.frame.setLocation((int) ((screenSize.getWidth() / 2) - (frame.getSize().getWidth() / 2)), (int) ((screenSize.getHeight() / 2) - (frame.getSize().getHeight() / 2)));
		this.frame.setVisible(true);
	}
	
	
	
	private void initMenuPages() {
		this.menuPages.add(new MenuPage(this));
		this.menuPages.add(new NagelSimulationFrame(this));	
		this.menuPages.add(new SimulationFrame(this));
	}

	
	
	public void showMenuPage(int index) {
		menuPages.get(menuIndex).deactivate();
		menuPages.get(index).activate();
		menuIndex = index;
		this.showPanel((JComponent)this.menuPages.get(index));
	}

	
	
	public void showPanel(JComponent panel) {
		this.frame.setContentPane(panel);
		this.frame.pack();
	}
	
	public Map getCurrentMap(){
		return currentMap;
	}
	
	public void setCurrentMap(Map map) {
		currentMap = map;
	}
	
	public void toggleShowRedLight(){
		showLights = !showLights;
	}
	
	public boolean shouldShowLight(){
		return showLights;
	}
	public NagelMap getNagelMap() {
		if(nagelMap == null){
			nagelMap = new NagelMap();
			nagelMap.generate();
		}
		return nagelMap;
	}
	
	public void setNagelMap(NagelMap map){
		nagelMap = map;
		for(TickListener tl: tickListeners){
			nagelMap.addTickListener(tl);
		}
	}
	
	public void addTickListener(TickListener tl){
		tickListeners.add(tl);
		nagelMap.addTickListener(tl);
	}

}
