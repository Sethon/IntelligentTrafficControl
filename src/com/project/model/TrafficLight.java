package com.project.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TrafficLight extends JPanel{
	Color onLight; // To indicate which color light is on
	int TLWidth = 0; // Width of traffic light
	int TLHeight; // Height of traffic light
	int xOrigin; // Coordinates of upper left-hand corner of
	int yOrigin; // traffic light
	int xLCoor; // x coordinate of lights
	int yLCoor; // y coor of red light
	int LDiam; // Diameter of each light
	int interLSpace; // Space between lights

	public TrafficLight() // Zero-parameter constructor
	{
		onLight = Color.green;
	}

	public TrafficLight(Color c) // One-parameter constructor
	{
		setOnLight(c); // Verify color setting
	}

	public Color getOnLight() {
		return onLight;
	}

	public void setOnLight(Color c) {
		// Setters and constructors should insure that class variables
		// are set to valid values.
		if (c == Color.red || c == Color.yellow || c == Color.green) {
			onLight = c;
		} else {
			System.out.println("TrafficLight.setOnLight:  cannot set " + "traffic light to color " + c
					+ "\nSetting color to default green.");
			onLight = Color.green;
		}
	}

	public void setColor(Color c) {
		setOnLight(c);
		repaint();
	}

	/************************************************/
	public void paint(Graphics g) {
		System.out.println("Painting traffic light again"); // To see when
		// paint() is called
		if (TLWidth == 0) {
			Dimension d = this.getSize(); // Get size of canvas
			System.out.println("d = " + d);
			TLWidth = d.width / 2; // Set width of traffic light
			TLHeight = 3 * d.height / 4; // Set height of traffic light
			xOrigin = (d.width - TLWidth) / 2; // Center traffic light on canvas
			yOrigin = (d.height - TLHeight) / 2;
			LDiam = TLHeight / 6; // Diameter of each light
			xLCoor = xOrigin + (TLWidth - LDiam) / 2; // x coordinate of lights
			interLSpace = (TLHeight - 3 * LDiam) / 4; // Space between lights
			yLCoor = yOrigin + interLSpace; // y coor of red light
		}

		Color colorSave = g.getColor(); // Save current color

		// Draw outline of traffic light
		g.setColor(Color.lightGray);
		g.fill3DRect(xOrigin, yOrigin, TLWidth, TLHeight, true);

		Color Red, Yellow, Green; // Colors to change light to
		// Change the light
		if (onLight == Color.red) {
			Red = turnOn(Color.red);
			Yellow = turnOff(Color.yellow);
			Green = turnOff(Color.green);
		} else if (onLight == Color.yellow) {
			Red = turnOff(Color.red);
			Yellow = turnOn(Color.yellow);
			Green = turnOff(Color.green);
		} else {
			Red = turnOff(Color.red);
			Yellow = turnOff(Color.yellow);
			Green = turnOn(Color.green);
		}

		// Now color the lights. onLight is bright others are darkened.

		g.setColor(Red);
		g.fillOval(xLCoor, yLCoor, LDiam, LDiam);

		g.setColor(Yellow);
		g.fillOval(xLCoor, yLCoor + LDiam + interLSpace, LDiam, LDiam);

		g.setColor(Green);
		g.fillOval(xLCoor, yLCoor + 2 * LDiam + 2 * interLSpace, LDiam, LDiam);

		// Now draw black outline around each light
		g.setColor(Color.black);
		// Red light
		g.drawOval(xLCoor, yLCoor, LDiam, LDiam);
		// Yellow light
		g.drawOval(xLCoor, yLCoor + LDiam + interLSpace, LDiam, LDiam);
		// Green light
		g.drawOval(xLCoor, yLCoor + 2 * LDiam + 2 * interLSpace, LDiam, LDiam);

		g.setColor(colorSave); // Restore original color
	}

	/************************************************/
	Color turnOn(Color c) {
		return c.brighter().brighter();
	}

	Color turnOff(Color c) {
		return c.darker().darker();
	}

	/************************************************/
	// Inform layout managers of minimum and preferred sizes of canvas
	// IMPORTANT: be sure to spell the names of these functions correctly
	// or else they will not be over ridden and will have no effect.

	public Dimension getMinimumSize() {
		System.out.println("getMinimumSize() called");
		return new Dimension(100, 100);
	}

	/************************************************/
	public Dimension getPreferredSize() {
		System.out.println("getPreferredSize() called");
		return new Dimension(150, 300);
	}
}
