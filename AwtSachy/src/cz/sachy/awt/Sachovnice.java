package cz.sachy.awt;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import cz.sachy.mysleni.Minimax;
import cz.sachy.pravidla.Pozice;

@SuppressWarnings("serial")
public class Sachovnice extends Component  implements KeyListener {
	int mPoleXY;
	int mOdstup;
	int mHrana;
	Pozice mPozice = new Pozice();
	Image bk, ck, bp, cp, bs, cs, bj, cj, bd, cd, bv, cv;
	boolean mOtoceno;
	int mcx = 4;
	int mcy = 4;
	int mox = -1;
	int moy = -1;
	byte[] mSchPriMysleni = new byte[Pozice.h8 + 1];
	boolean mBilyClovek = true;
	boolean mCernyClovek = false;
	boolean mPremyslim = false;
	
	protected Image loadImage(String img) {
		URL url = this.getClass().getResource("figury/" + img + ".png");
		return Toolkit.getDefaultToolkit().getImage(url);
	}

	
	public Sachovnice(int poleXY, int odstup) {
		mPoleXY = poleXY;
		mOdstup = odstup;
		mHrana = 8 * poleXY + 2 * odstup;
		mOtoceno = false;
		bk = loadImage("bk");
		bd = loadImage("bd");
		bv = loadImage("bv");
		bs = loadImage("bs");
		bj = loadImage("bj");
		bp = loadImage("bp");
		ck = loadImage("ck");
		cd = loadImage("cd");
		cv = loadImage("cv");
		cs = loadImage("cs");
		cj = loadImage("cj");
		cp = loadImage("cp");
		

		MediaTracker mediaTracker = new MediaTracker(this);
		mediaTracker.addImage(bk, 0);
		mediaTracker.addImage(ck, 1);
		mediaTracker.addImage(bp, 2);
		mediaTracker.addImage(cp, 3);
		mediaTracker.addImage(bs, 4);
		mediaTracker.addImage(cs, 5);
		mediaTracker.addImage(bj, 6);
		mediaTracker.addImage(cj, 7);
		mediaTracker.addImage(bd, 8);
		mediaTracker.addImage(cd, 9);
		mediaTracker.addImage(bv, 10);
		mediaTracker.addImage(cv, 11);
		try
		{
		    mediaTracker.waitForAll();
		}
		catch (InterruptedException ie)
		{
		    System.exit(1);
		}
	}
	
	private int getX(int i) {
		if (mOtoceno) i = 7 - i;
		return mOdstup + mPoleXY * i;
	}
	
	private int getY(int j) {
		if (!mOtoceno) j = 7 - j;
		return mOdstup + mPoleXY * j;
	}
	
	
	public boolean isPremyslim() {
	  	return mPremyslim;
	}
	protected boolean hrajeClovek() {
		return !isPremyslim() && (mBilyClovek && mPozice.bily ||
			mCernyClovek && !mPozice.bily);
	}
	
	public void paint(Graphics g) {
	    g.clearRect(0, 0, mHrana, mHrana);
	    
	    
	    Color cerna = new Color(150, 110, 110); 
	    Color bila = new Color(220, 220, 100);
	    Color modra = new Color(0, 0, 255);
        Color zelena = new Color(0, 255, 0);
	    boolean clovek = hrajeClovek();
	    
	    g.setColor(cerna);
	    for (int i = 0; i < 8; i++) {
	      for (int j = 0; j < 8; j++) {
        	Color c;
            if (clovek && mox == i && moy == j) {
           		c = zelena;
           	} else
           	if (clovek && mcx == i && mcy == j) {
           		c = modra;
           	} else {
           		c = ((((i + j) & 1) == 1) ? bila : cerna);
           	}
           	g.setColor(c);
	        g.fillRect(getX(i), getY(j), mPoleXY, mPoleXY);
	        }
	    }
	    
	    for (int i = 0; i < 8; i++) {
	      for (int j = 0; j < 8; j++) {
	        int p = Pozice.a1 + i + j * 10;
	        byte f = mPremyslim ? mSchPriMysleni[p] : mPozice.sch[p];
	        if (f == 0) continue;

	      
	        int x = getX(i) + (mPoleXY - bp.getWidth(null)) / 2;
	        int y = getY(j) + (mPoleXY - bp.getHeight(null)) / 2;
	        
	        switch (f) {
	        case 1:
	          g.drawImage(bp, x, y, null);
	          break;
	        case 2:
	          g.drawImage(bj, x, y, null);
	          break;
	        case 3:
	          g.drawImage(bs, x, y, null);
	          break;
	        case 4:
	          g.drawImage(bv, x, y, null);
	          break;
	        case 5:
	          g.drawImage(bd, x, y, null);
	          break;
	        case 6:
	          g.drawImage(bk, x, y, null);
	          break;
	        case -1:
	          g.drawImage(cp, x, y, null);
	          break;
	        case -2:
	          g.drawImage(cj, x, y, null);
	          break;
	        case -3:
	          g.drawImage(cs, x, y, null);
	          break;
	        case -4:
	          g.drawImage(cv, x, y, null);
	          break;
	        case -5:
	          g.drawImage(cd, x, y, null);
	          break;
	        case -6:
	          g.drawImage(ck, x, y, null);
	          break;
	        }
	      /*  if (p == oznaceno) {
	          g.setColor(new Color(0, 0, 0));
	          g.fillRect(x + 3, y + 3, pole - 6, 3);
	          g.fillRect(x + 3, y - 6 + pole, pole - 6, 3);
	          g.fillRect(x + 3, y + 3, 3, pole - 6);
	          g.fillRect(x + pole - 6, y + 3, 3, pole - 6);
	          //g.drawLine(x, y, x, y + this.pole);
	        }*/
	      }
	    }
	    
	  }
	
	 public Dimension getMaximumSize() {
		 return getPreferredSize();
	}
		  
	public Dimension getMinimumSize() {
	    return getPreferredSize();
	}
		  
	public Dimension getPreferredSize() {
		return new Dimension(mHrana, mHrana);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		int code = k.getKeyCode();
		switch (code) {
		case 37:
			if (mOtoceno && mcx < 7 || !mOtoceno && mcx > 0) {
    			if (mOtoceno) mcx++; else mcx--;
    			repaint();
    		}
			break;
		case 38:
			if (!mOtoceno && mcy < 7 || mOtoceno && mcy > 0) {
    			if (mOtoceno) mcy--; else mcy++;
    			repaint();
    		}
			break;
		case 39:
			if (!mOtoceno && mcx < 7 || mOtoceno && mcx > 0) {
    			if (mOtoceno) mcx--; else mcx++;
    			repaint();
    		}
			break;
		case 40:
			if (mOtoceno && mcy < 7 || !mOtoceno && mcy > 0) {
    			if (mOtoceno) mcy++; else mcy--;
    			repaint();
    		}
			break;
		case 10:
			if (!hrajeClovek()) return;
    		Vector t = mPozice.nalezTahy();
    		int pole = Pozice.a1 + mcx + 10 * mcy;
    		if (mPozice.JeTam1(t, pole)) {
    			mox = mcx;
    			moy = mcy;
    			repaint();
    			return;
    		}
    		int pole1 = Pozice.a1 + mox + 10 * moy;
    		if (mPozice.JeTam2(t, pole1, pole)) {
    			int tah = mPozice.DoplnTah(t, pole1, pole);
    			tahni(tah);
    			return;
    		}
  		}
	}
	
	public void tahni(int tah) {
    	mox = -1;
    	moy = -1;
    	mPozice.tahni(tah, true, true, null);
    	mPozice.nalezTahy();
    	repaint();
    	pripravTah();
    }

	public void pripravTah() {
    	SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				pripravTahHned();
			}
    	});
    }
	
	protected void pripravTahHned() {
    	if (hrajeClovek()) {
    		mPozice.nalezTahy();
    	} else {
			tahniPrograme();
    	}
    }
	
	protected void tahniPrograme() {
    	mPremyslim = true;
    	System.arraycopy(mPozice.sch, 0, mSchPriMysleni, 0, Pozice.h8 + 1);
    	Thread t = new Thread() {
    		 public void run() {
    			 mPozice.nalezTahy();
    			 final int tah;
    			 tah = Minimax.minimax(mPozice, 5000); 
    			 SwingUtilities.invokeLater(
    					 new Runnable() {

							public void run() {
								mPremyslim = false;
								if (tah != 0) 
									tahni(tah);
								//else
									//dlg("The end");
							}}
    					 );
    		 }
    	};
    	t.start();
     }
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
