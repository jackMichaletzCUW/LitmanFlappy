import java.awt.image.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MatrixTrail
{
	String trail;
	int modNum;
	
	public double xpos = 0;
	double velocity = 0;
	
	Font f;
	
	Random r;
	
	BufferedImage img;
	BufferedImage CLEAR;
	
	int cp = 0;
	int maxlen = 0;
	
	public int color;
	public Color c;
	
	private int wc_int;
	private Color wc;
	
	private final int MAX_FONT = 13;
	private final int MIN_FONT = 6;
		
	private int fontSize = 0;
		
	public int strength;
	
	public MatrixTrail lson;
	public MatrixTrail rson;
	
	public ArrayList<MatrixTrail> sorted;
	
	private int count = 1;
	private int countAtLastSort = 1;
	
	public MatrixTrail(int xpos)
	{
		r = new Random();
		trail = "";
						
		this.xpos = xpos;
		
		strength = r.nextInt(0xFF);
	
		velocity = (double)strength / 0xFF;
	
		fontSize = MIN_FONT + (int)(velocity * (MAX_FONT - MIN_FONT));
		
		maxlen = fontSize * 1;
		
		int big_strength = (int)(strength * 1.8);
		if(big_strength > 0xFF)
			big_strength = 0xFF;
		
		color = (0xAA << 24) | (strength << 8) | ((0xFF - strength) / 6);
		
		c = new Color(color);
		
		wc_int = (big_strength << 24) | (strength << 16) | (big_strength << 8) | big_strength;
		
		wc = new Color(wc_int);
		
		f = new Font("sans-serif", Font.PLAIN, fontSize);
				
		modNum = r.nextInt(FlappyBird.FRAME_WRAP);
		
		img = new BufferedImage(20, FlappyBird.height, BufferedImage.TYPE_4BYTE_ABGR);
		CLEAR = new BufferedImage(20, FlappyBird.height, BufferedImage.TYPE_4BYTE_ABGR);
		
		sorted = new ArrayList<MatrixTrail>();
		sorted.add(this);
		
		addLetter();
		
		if(modNum == 0)
			modNum = 1;
	}
	
	public void add(MatrixTrail m)
	{
		count++;
		
		if(m.strength > strength)
		{
			if(rson == null)
				rson = m;
			else
				rson.add(m);
		}
		else
		{
			if(lson == null)
				lson = m;
			else
				lson.add(m);
		}
	}
	
	public ArrayList<MatrixTrail> getSorted()
	{
		if(countAtLastSort != count)
			sort();
		
		return sorted;
	}
	
	public void updateChildren()
	{
		if(lson != null)
			lson.updateChildren();
		
		update();
		
		if(rson != null)
			rson.updateChildren();
	}
	
	private void sort()
	{
		sorted = new ArrayList<MatrixTrail>();
		
		if(lson != null)
			sorted.addAll(lson.getSorted());
		
		sorted.add(this);
		
		if(rson != null)
			sorted.addAll(rson.getSorted());
		
		countAtLastSort = count;
	}
	
	public void clear()
	{
		trail = "";
		cp = 0;
		
		img.setData(CLEAR.getRaster());
		
		modNum = r.nextInt(FlappyBird.FRAME_WRAP);
		
		if(modNum == 0)
			modNum = 1;
	}
	
	private void fade()
	{
		int bounds = (cp >= img.getHeight() ? img.getHeight() : cp - maxlen);
		
		for(int y = 0; y < bounds; y++)
		{
			for(int x = 0; x < 20; x++)
			{
				int pxl = img.getRGB(x, y);
				
				int a = 0xFF & (pxl >> 24);
				
				if(a > 0xA)
				{
					img.setRGB(x, y, ((0xFF & (a - 0xA)) << 24) | (0xFFFFFF & pxl));
				}
				else if(a != 0x0)
				{
					img.setRGB(x, y, 0xFFFFFF & pxl);
				}
			}
		}
	}
	
	public void drawLetter()
	{		
		Graphics g = img.createGraphics();
		
		g.setColor(c);
				
		g.setFont(f);
				
		if(trail.length() > 1)
		{
			g.drawString("" + trail.charAt(trail.length() - 2), 0, cp);
			cp += fontSize;
		}

		//((Graphics2D)g).setRenderingHint(
		//        RenderingHints.KEY_TEXT_ANTIALIASING,
		//        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		
		g.setColor(wc);
		g.drawString(trail.charAt(trail.length() - 1) + "", 0, cp);
				
		g.dispose();
	}
	
	public void update()
	{
		if(FlappyBird.FRAME % modNum == 0)
		{
			addLetter();

			fade();
		}
		
		xpos -= (velocity * Pipe.ADVANCE_AMOUNT);
		
		if(xpos < (-1 * img.getWidth()))
		{
			xpos = FlappyBird.width + img.getWidth();
			
			if(cp >= FlappyBird.height - 15)
				clear();
		}
	}
	
	private void addLetter()
	{
		double selector = Math.random();
		
		if(selector < 0.7)
			trail += ((char)(r.nextInt(0x5F) + 0x30A0)); // japanese
		else if(selector < 0.9)
			trail += (char)(r.nextInt(0x38) + 0x391); // greek
		else
			trail += ((char)(r.nextInt(0x1F) + 0x20)); // numbers/symbols
		
		drawLetter();
	}
}
