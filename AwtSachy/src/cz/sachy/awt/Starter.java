package cz.sachy.awt;

import java.applet.Applet;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


@SuppressWarnings("serial")
class SachFrame extends Frame implements WindowListener {

	public SachFrame(String string) {
		super(string);
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
public class Starter extends Applet {

	
	public static void otevriOkno() {
		SachFrame f = new SachFrame("Honzovy šachy for AWT");
		f.setSize(400,400);
		f.addWindowListener(f);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		otevriOkno();
	}
	
	public void init()
    {
		otevriOkno();
    } 

}
