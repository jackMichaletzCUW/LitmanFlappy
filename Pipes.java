import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.ImageIcon;

public class Pipes 
{
	private static LinkedList<Pipe> pipes;
	
	public static final int PIPE_WIDTH = 60;
	public static BufferedImage PipedImage;
	
	private static ImageIcon topdog;
	private static ImageIcon lowdog;
	
	public static void initializePipes()
	{
		topdog = new ImageIcon("res/Pipe/longdogt.png");
		lowdog = new ImageIcon("res/Pipe/longdogb.png");
		
		PipedImage = new BufferedImage(FlappyBird.width, FlappyBird.height, BufferedImage.TYPE_4BYTE_ABGR);
		
		pipes = new LinkedList<Pipe>();
		
		pipes.add(new Pipe(115));
		pipes.add(new Pipe(200));
	}
	
	private static int getLeftTopY()
	{
		return 0 - (int)(((100 - pipes.getFirst().topSize) * (topdog.getIconHeight() / 100)));
	}
	
	private static int getRightTopY()
	{
		return 0 - (int)(((100 - pipes.getLast().topSize) * (topdog.getIconHeight() / 100)));
	}
	
	private static int getLeftLowY()
	{
		return FlappyBird.height - (int)(((pipes.getFirst().bottomSize) * (lowdog.getIconHeight() / 100)));
	}
	
	private static int getRightLowY()
	{
		return FlappyBird.height - (int)(((pipes.getLast().bottomSize) * (lowdog.getIconHeight() / 100)));
	}
	
	public static void updateGFX()
	{
		PipedImage = new BufferedImage(FlappyBird.width, FlappyBird.height, BufferedImage.TYPE_4BYTE_ABGR);
		
		Graphics gb = PipedImage.createGraphics();
		
		// draw top half of left pipe
		topdog.paintIcon(null, gb, (int)((FlappyBird.width / 100) * getLeftPipePosition()), getLeftTopY());
		
		// draw bottom half of left pipe
		lowdog.paintIcon(null, gb, (int)((FlappyBird.width / 100) * getLeftPipePosition()), getLeftLowY());
		
		// draw top half of right pipe
		topdog.paintIcon(null, gb, (int)((FlappyBird.width / 100) * getRightPipePosition()), getRightTopY());
		
		// draw bottom half of right pipe
		lowdog.paintIcon(null, gb, (int)((FlappyBird.width / 100) * getRightPipePosition()), getRightLowY());

		gb.dispose();
	}
	
	
	public static void movePipes()
	{
		if(pipes.getFirst().advance())
		{
			pipes.addLast(pipes.pop());
			
			pipes.getFirst().advance();
		}
		else
		{
			pipes.getLast().advance();
		}
	}
	
	public static double getLeftPipeGap()
	{
		return pipes.getFirst().getGap();
	}
	
	public static double getRightPipeGap()
	{
		return pipes.getLast().getGap();
	}
	
	public static double getLeftPipePosition()
	{
		return pipes.getFirst().pipePosition;
	}
	
	public static double getRightPipePosition()
	{
		return pipes.getLast().pipePosition;
	}
	
	public static double getLeftPipeTopSize()
	{
		return pipes.getFirst().topSize;
	}
	
	public static double getLeftPipeBottomSize()
	{
		return pipes.getFirst().bottomSize;
	}
	
	public static double getRightPipeTopSize()
	{
		return pipes.getLast().topSize;
	}
	
	public static double getRightPipeBottomSize()
	{
		return pipes.getLast().bottomSize;
	}
}
