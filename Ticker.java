import java.util.*;

public class Ticker 
{
	Timer timer;
	
	public Ticker()
	{
		timer = new Timer();
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				FlappyBird.update();
				
				if(FlappyBird.gameGoing)
				{
					if(CollisionDetector.isCollision(50))
					{
						//timer.cancel();
						FlappyBird.gameGoing = false;
						TheMatrix.redShift();
					}
				}
			}
		}, 0, 20);
	}
}
