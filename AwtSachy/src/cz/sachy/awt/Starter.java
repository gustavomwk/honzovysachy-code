package cz.sachy.awt;

import java.applet.Applet;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


@SuppressWarnings("serial")
class SachFrame extends Frame implements WindowListener {
	boolean mInApplet;
	public SachFrame(String string, boolean inApplet) {
		super(string);
		mInApplet = inApplet;
		//add(new Label("Does not work yet, please wait for the next version."));
		Sachovnice s = new Sachovnice(40, 10); 
		add(s);
		addKeyListener(s);
		pack();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		if (mInApplet) 
			this.dispose();
		else
			System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

@SuppressWarnings("serial")
public class Starter extends Applet implements MouseListener {

	
	public static void otevriOkno(boolean inApplet) {
		SachFrame f = new SachFrame("Honzovy šachy for AWT", inApplet);
		f.addWindowListener(f);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		otevriOkno(false);
	}
	
	public void init()
    {
		Sachovnice s = new Sachovnice(40, 10);
		addKeyListener(s);
		requestFocus();
		add(s);
		addMouseListener(this);
		
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		requestFocus();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		requestFocus();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	} 

}
