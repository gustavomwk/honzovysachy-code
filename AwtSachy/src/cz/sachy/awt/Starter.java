package cz.sachy.awt;

import java.applet.Applet;
import java.awt.Frame;
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
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
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
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}

@SuppressWarnings("serial")
public class Starter extends Applet  {

	
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
		add(s);
    }
}
