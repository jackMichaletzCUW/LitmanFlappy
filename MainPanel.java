import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
 
public class MainPanel extends JPanel
{
	int width;
	int height;
	
	Font gofl;
	Font gofs;
	
	double gov = 2.5;
	double gop = FlappyBird.height;
	
	public MainPanel(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		this.setSize(width, height);
		this.setBackground(Color.BLACK);
		
		gofl = new Font("Courier", Font.BOLD, 30);
		gofs = new Font("Courier", Font.BOLD, 15);

	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Bird.updateGFX((int)((height - Bird.normalHeight) * (Bird.position / Bird.MAX_POSITION)));
		Pipes.updateGFX();
				
		for(MatrixTrail line : TheMatrix.head.getSorted())
		{
			g.drawImage(line.img, (int)line.xpos, 0, null);
		}
		
		g.drawImage(Pipes.PipedImage,0,0,null);
		g.drawImage(Bird.BirdImage,50,0,null);
		
		g.setColor(Color.YELLOW);
				
		Graphics2D g2d = (Graphics2D)g;
        AffineTransform transform = g2d.getTransform();
        
        transform.translate((FlappyBird.score > 9 ? 125 : 150), 50);
        g2d.transform(transform);
        
        if(FlappyBird.gameGoing)
        	g2d.setColor(Color.green);
        else
        	g2d.setColor(Color.white);
        
        FontRenderContext frc = g2d.getFontRenderContext();
        TextLayout tl = new TextLayout(FlappyBird.score + "", g.getFont().deriveFont(45F), frc);
        Shape shape = tl.getOutline(null);
        g2d.setStroke(new BasicStroke(5f));
        g2d.draw(shape);
        g2d.setColor(Color.black);
        g2d.fill(shape);
        
		if(!FlappyBird.gameGoing)
		{
			if(gop > FlappyBird.height / 3)
				gop -= gov;
			
			g.setColor(Color.WHITE);
			g.setFont(gofl);
			g.drawString("GAME OVER", (FlappyBird.score > 9 ? -107 : -132), (int)gop);
			g.setFont(gofs);
			g.drawString("PRESS \"R\" TO RESTART GAME", (FlappyBird.score > 9 ? -115 : -140), (int)gop + 50);
		}
		else
		{
			gop = FlappyBird.height;
			g.setColor(Color.RED);
		}

		g.dispose();
	}
}
