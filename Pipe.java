import java.util.Random;

public class Pipe 
{
	public double gapPosition;
	
	public double pipePosition;
	
	public int topSize;
	public int bottomSize;
	
	private final double MIN_PIPE_POSITION = -35.0;
	private final double MAX_PIPE_POSITION = 135.0;
	
	private final int MIN_PIPE_SIZE = 10;
	
	private final int MAX_TOP_SIZE = 70;
	private final int MAX_BOTTOM_SIZE = 70;
	
	private int MIN_GAP_SIZE = 40;
	private int MAX_GAP_SIZE = 55;
	
	private final int MIN_MIN_GAP_SIZE = 28;
	private final int MIN_MAX_GAP_SIZE = 35;
	
	public static double ADVANCE_AMOUNT = 1.0;
	private static double ADVANCE_INCREASE_AMOUNT = 0.03;
	private static double MAX_ADVANCE_AMOUNT = 1.75;
	
	private Random r;
		
	public Pipe(int initialPosition)
	{
		pipePosition = initialPosition;
		r = new Random();
		
		topSize = 25;
		bottomSize = 25;
	}
	
	public double getGap()
	{
		return 100.0 - (topSize + bottomSize);
	}
	
	private void reinitialize()
	{
		TheMatrix.addTrail(2);
		
		pipePosition = MAX_PIPE_POSITION;
		
		if(ADVANCE_AMOUNT < MAX_ADVANCE_AMOUNT)
			ADVANCE_AMOUNT += ADVANCE_INCREASE_AMOUNT;
		
		if(MIN_GAP_SIZE > MIN_MIN_GAP_SIZE)
			MIN_GAP_SIZE--;
		
		if(MAX_GAP_SIZE > MIN_MAX_GAP_SIZE)
			MAX_GAP_SIZE--;
		
		// make new gap
		do
		{
			/*double delta = MAX_GAP_VARIANCE * Math.random();
			double newGap = currentGap - delta;
			
			if(newGap < MIN_GAP_SIZE)
				newGap = MIN_GAP_SIZE;
						
			double balance = Math.random();
			
			if(balance < 0.5 && (bottomSize > MIN_PIPE_SIZE))
				bottomSize += delta;
			else
				topSize += delta;*/
			
			topSize = r.nextInt((int)(MAX_TOP_SIZE - MIN_PIPE_SIZE)) + MIN_PIPE_SIZE;
			bottomSize = r.nextInt((int)(MAX_BOTTOM_SIZE - MIN_PIPE_SIZE)) + MIN_PIPE_SIZE;
			
		} while(getGap() < MIN_GAP_SIZE || getGap() > MAX_GAP_SIZE);		
	}
	
	public boolean advance()
	{
		if(pipePosition < MIN_PIPE_POSITION)
		{
			reinitialize();
			return true;
		}
		else
		{
			pipePosition -= ADVANCE_AMOUNT;
			return false;
		}
	}
}
