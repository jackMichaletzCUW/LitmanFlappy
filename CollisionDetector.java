import java.awt.image.*;
import java.awt.*;

public class CollisionDetector
{	
	public static boolean lastFrameCollidable = false;
	
	public static void reset()
	{
		lastFrameCollidable = false;
	}
	
	private static boolean is_col(int x, int y, int bo)
	{
        // if bird pixel isnt black and pipe pixel isnt either then there is a collision
		return (Bird.BirdImage.getRGB(x, y) != 0x00000000) && (Pipes.PipedImage.getRGB(bo + x, y) != 0x00000000);
	}
	
	public static void updateScore(int firstBirdPXL_X)
	{
		int x = firstBirdPXL_X;
		boolean anythingFound = false;
		
		for(int y = 0; y < Pipes.PipedImage.getHeight(); y++)
		{
			if(Pipes.PipedImage.getRGB(x, y) != 0x00000000)
			{
				anythingFound = true;
				break;
			}
		}
		
		if(anythingFound)
		{
			lastFrameCollidable = true;
		}
		else
		{
			if(lastFrameCollidable)
			{
				FlappyBird.score++;
			}
			
			lastFrameCollidable = false;
		}
	}
	
	public static boolean isCollision(int birdOffset)
	{		
		for (int x = 0; x < Bird.BirdImage.getWidth(); x++)
		{
			for(int y = 0; y < Bird.BirdImage.getHeight(); y++)
			{
				if(is_col(x, y, birdOffset))
				{
					return true;
				}
			}
		}
		
		return false;
	}
}
