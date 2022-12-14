package com.SwingCalendar;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Add new bar to chart
 * @version 13/12/2022
 *
 */
public class BarChart extends JPanel{
	private Map<Color, Integer> bars = new LinkedHashMap<Color, Integer>();

	/**
	 * Add new bar to chart
	 * @param color is the color to display bar
	 * @param value is the size of bar
	 */
	public void addBar(Color color, int value)
	{
		bars.put(color, value);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		//determine longest bar
		
		int max = Integer.MIN_VALUE;
		for (Integer value : bars.values())
		{
			max = Math.max(max, value);
		}

		//paint bars
		int width = (getWidth() / bars.size()) - 2;
		int x = 1;
		for (Color color : bars.keySet())
		{
			int value = bars.get(color);
			int height = (int)
					((getHeight()-5) * ((double)value / max));
			g.setColor(color);
			g.fillRect(x, getHeight() - height, width, height);
			g.setColor(Color.black);
			g.drawRect(x, getHeight() - height, width, height);
			x += (width + 2);
		}
	}

	@Override

	public Dimension getPreferredSize() {
		return new Dimension(bars.size() * 500, 400);
	}
}