import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import java.util.*;

public class Bird 
{
	
	public static double velocity = 0.0;
	public static double position = 0.0;
	
	public static final double MIN_POSITION = 0.0;
	public static final double MAX_POSITION = 100.0;
	
	public static final double FLAP_VELOCITY = -2.5;
	
	public static final int BIRD_DIM = 72;
	
	public static final String IMG_PATH = "res/bird/litman-";
		
	public static BufferedImage BirdImage;
	
	private static int currIndex = 0;
	public static int normalHeight = 65;
	
	public static ImageIcon[] sprites = new ImageIcon[14];
	
	public static void initSprites()
	{
		sprites[0] = new ImageIcon(IMG_PATH + "d-0.png");
		sprites[1] = new ImageIcon(IMG_PATH + "d-1.png");
		sprites[2] = new ImageIcon(IMG_PATH + "d-2.png");
		sprites[3] = new ImageIcon(IMG_PATH + "d-3.png");
		sprites[4] = new ImageIcon(IMG_PATH + "d-4.png");
		sprites[5] = new ImageIcon(IMG_PATH + "d-5.png");
		sprites[6] = new ImageIcon(IMG_PATH + "d-6.png");
		sprites[7] = new ImageIcon(IMG_PATH + "u-0.png");
		sprites[8] = new ImageIcon(IMG_PATH + "u-1.png");
		sprites[9] = new ImageIcon(IMG_PATH + "u-2.png");
		sprites[10] = new ImageIcon(IMG_PATH + "u-3.png");
		sprites[11] = new ImageIcon(IMG_PATH + "u-4.png");
		sprites[12] = new ImageIcon(IMG_PATH + "u-5.png");
		sprites[13] = new ImageIcon(IMG_PATH + "u-6.png");
	}
	
	public static ImageIcon getSprite()
	{
		int index = 0;
		//String path = "";
		
		if(velocity > 0)
		{
			//path += "d-";
			index = Math.abs((int)(velocity * 2.0));

			if(index > 6)
				index = 6;
		}
		else
		{
			if(currIndex < 7)
			{
				index = 7;
				//path = "u-";
			}
			else
			{
				index = 7;
				//path += "u-";
				index += Math.abs((int)(velocity * 2.0));
				
				if(index > 13)
					index = 13;
			}
		}
		
		//path += index + ".png";
		
		currIndex = index;
		
		return sprites[index];		
	}
	
	public static void updateGFX(int yPos)
	{
		BirdImage = new BufferedImage(BIRD_DIM, FlappyBird.height, BufferedImage.TYPE_4BYTE_ABGR);
		
		Graphics gb = BirdImage.createGraphics();
		//gb.setColor(new Color(0x00FFFFFF));
		//gb.fillRect(0, 0, Bird.BIRD_DIM, FlappyBird.height);
		ImageIcon sprite = getSprite();
		
		sprite.paintIcon(null, gb, (BIRD_DIM - sprite.getIconWidth()) / 2, yPos);
		gb.dispose();
	}
	
		
	public static void updateVP()
	{
		// Only keep accelerating if terminal velocity has not been reached
		if(Math.abs(velocity) + Physics.GRAVITY_ACCELERATION < Physics.TERMINAL_VELOCITY)
		{
			velocity -= Physics.GRAVITY_ACCELERATION;
		}
		
		// Only move bird if it doesn't put the bird out of bounds
		if((position + velocity >= MIN_POSITION) && (position + velocity <= MAX_POSITION))
		{
			position += velocity;
		}
		else
		{
			if(position + velocity < MIN_POSITION)
			{
				position = MIN_POSITION;
			}
			else
			{
				position = MAX_POSITION;
			}
			
			velocity = 0.0;
		}
	}
	
	public static void flap()
	{
		velocity = FLAP_VELOCITY;
	}

}
