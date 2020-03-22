import javax.swing.*;

public class MainWindow extends JFrame
{
	public MainPanel mp;
	
	public MainWindow(int width, int height)
	{
		mp = new MainPanel(width, height);
		
		this.setContentPane(mp);
		
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addKeyListener(new Keys());
		
		this.setVisible(true);
	}
}
