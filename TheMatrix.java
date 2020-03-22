import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class TheMatrix 
{
	public static MatrixTrail head;
	
	private static Random r;
	
	public static void init(int num)
	{
		//Lines = new ArrayList<MatrixTrail>();
		r = new Random();
		
		head = new MatrixTrail(r.nextInt(FlappyBird.width));
		
		for(int i = 0; i < num - 1; i++)
		{
			head.add(new MatrixTrail(r.nextInt(FlappyBird.width)));
		}
		
		/*Lines.sort(new Comparator<MatrixTrail>() {

			public int compare(MatrixTrail o1, MatrixTrail o2) 
			{
				return o1.strength - o2.strength;
			}
		});*/
	}
	
	/*public static int getSize()
	{
		return Lines.size();
	}*/
	
	public static void addTrail(int num)
	{
		for(int i = 0; i < num; i++)
		{
			head.add(new MatrixTrail(r.nextInt(FlappyBird.width)));
		}
		
		/*Lines.sort(new Comparator<MatrixTrail>() {

			public int compare(MatrixTrail o1, MatrixTrail o2) 
			{
				return o1.strength - o2.strength;
			}
		});*/
	}
	
	public static void update()
	{
		head.updateChildren();
	}
	
	public static void redShift()
	{
		for(MatrixTrail line : head.getSorted())
		{
			for(int x = 0; x < line.img.getWidth(); x++)
			{
				for(int y = 0; y < line.img.getHeight(); y++)
				{
					int oc = line.img.getRGB(x, y);

					int rc = 0xFF & oc >> 16;
					
					if(rc == 0x00)
					{
						int ac = 0xFF & oc >> 24;
						int gc = 0xFF & oc >> 8;
						int bc = 0xFF & oc;
						
						line.img.setRGB(x, y, (ac << 24) | (gc << 16) | bc);
					}
				}
			}
		}
	}
}
