import java.awt.event.*;

public class Keys implements KeyListener
{
	boolean isKeyDown = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == e.VK_SPACE)
		{
			if(!isKeyDown)
			{
				isKeyDown = true;
				
				if(FlappyBird.gameGoing)
					Bird.flap();
			}
		}
		else if(e.getKeyCode() == e.VK_R)
		{
			FlappyBird.reinit();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == e.VK_SPACE)
		{
			isKeyDown = false;
		}
	}
	
}
