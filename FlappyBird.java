import java.util.Timer;
import java.util.TimerTask;

public class FlappyBird 
{
	static MainWindow window;
	public static int width;
	public static int height;
		
	public static int FRAME;
	public static final int FRAME_WRAP = 19;
	
	public static MatrixTrail test;
	
	public static boolean gameGoing = true;
	
	private static Ticker t;
	
	public static int score = 0;
	
	public static void main(String[] args)
	{
		width = Integer.parseInt(args[0]);
		height = Integer.parseInt(args[1]);
		
		Bird.initSprites();
		Pipes.initializePipes();
		
		TheMatrix.init(5);
		window = new MainWindow(width, height);
		
		Bird.updateGFX(0);
			
		t = new Ticker();
	}
	
	public static void reinit()
	{
		score = 0;
		CollisionDetector.reset();
		Bird.velocity = 0.0;
		Pipe.ADVANCE_AMOUNT = 1.0;
		t.timer.cancel();
		
		Pipes.initializePipes();
		
		TheMatrix.init(5);
		
		Bird.position = 0;
		
		Bird.updateGFX(0);
		
		gameGoing = true;
		
		t = new Ticker();
	}
	
	public static void update()
	{	
		Bird.updateVP();
		
		if(gameGoing)
		{
			if(++FRAME > FRAME_WRAP)
				FRAME = 0;
		
			TheMatrix.update();
			Pipes.movePipes();
		}
		
		CollisionDetector.updateScore(50);
		
		window.mp.repaint();
	}
}
