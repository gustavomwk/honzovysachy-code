/*
 This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License along
with this program; if not, write to the Free Software Foundation, Inc.,
51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package cz.sachy;

import java.util.Vector;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import cz.sachy.mysleni.Minimax;
import cz.sachy.pravidla.Pozice;

public class SachoveView extends View {
	int mcx = 4;
	int mcy = 4;
	int mox = -1;
	int moy = -1;
    int mPole = -1;
	boolean mOtoceno = false;
	boolean mBilyClovek = true;
	boolean mCernyClovek = false;
	boolean mPremyslim = false;
	Drawable mFigury[][];
	Pozice mPozice = new Pozice();
	final Handler mHandler = new Handler();
	byte[] mSchPriMysleni = new byte[Pozice.h8 + 1];
	
	protected boolean hrajeClovek() {
		return !isPremyslim() && (mBilyClovek && mPozice.bily ||
			mCernyClovek && !mPozice.bily);
	}
	
    public SachoveView(Activity a) {
        super(a);
        setFocusable(true);
        mFigury = new Drawable[2][];
        mFigury[0] = new Drawable[6];
        mFigury[1] = new Drawable[6];
        mFigury[0][0] = getContext().getResources().getDrawable(R.drawable.cp);
        mFigury[0][1] = getContext().getResources().getDrawable(R.drawable.cj);
        mFigury[0][2] = getContext().getResources().getDrawable(R.drawable.cs);
        mFigury[0][3] = getContext().getResources().getDrawable(R.drawable.cv);
        mFigury[0][4] = getContext().getResources().getDrawable(R.drawable.cd);
        mFigury[0][5] = getContext().getResources().getDrawable(R.drawable.ck);
        mFigury[1][0] = getContext().getResources().getDrawable(R.drawable.bp);
        mFigury[1][1] = getContext().getResources().getDrawable(R.drawable.bj);
        mFigury[1][2] = getContext().getResources().getDrawable(R.drawable.bs);
        mFigury[1][3] = getContext().getResources().getDrawable(R.drawable.bv);
        mFigury[1][4] = getContext().getResources().getDrawable(R.drawable.bd);
        mFigury[1][5] = getContext().getResources().getDrawable(R.drawable.bk);
        pripravTahHned();
    }
    
    public void pripravTah() {
    	mHandler.post(new Runnable() {
			public void run() {
				pripravTahHned();
			}
    	});
    }
    
    public void tahni(int tah) {
    	mox = -1;
    	moy = -1;
    	mPozice.tahni(tah, true, true, null);
    	mPozice.nalezTahy();
    	invalidate();
    	pripravTah();
    }
    
    protected void pripravTahHned() {
    	if (hrajeClovek()) {
    		mPozice.nalezTahy();
    	} else {
			tahniPrograme();
    	}
    	
    }
    
    public void otoc() {
    	mOtoceno = !mOtoceno;
    	invalidate();
    }
    
    public boolean onTouchEvent(MotionEvent event)  {
    	if (isPremyslim()) return false;
     	if (event.getAction() != MotionEvent.ACTION_DOWN) return false;
    	if (mPole <= 0 || !hrajeClovek()) return false;
    	int x = (int)((event.getX() + 0.5) / mPole);
    	int y = (int)((event.getY() + 0.5) / mPole);
    	if (mOtoceno) {
    		x = 7 - x;
    	} else {
    		y = 7 - y;
    	}

    	Vector t = mPozice.nalezTahy();
		int pole = Pozice.a1 + x + 10 * y;
		if (mPozice.JeTam1(t, pole)) {
			mcx = mox = x;
			mcy = moy = y;
			invalidate();
			return true;
		}
		int pole1 = Pozice.a1 + mox + 10 * moy;
		if (mPozice.JeTam2(t, pole1, pole)) {
			int tah = mPozice.DoplnTah(t, pole1, pole);
			tahni(tah);
			return true;
		}
		mcx = x;
		mcy = y;
		
		return true;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
    	byte[] sch = null;
    	if (isPremyslim()) {
    		sch = mSchPriMysleni;
    	} else {
    		sch = mPozice.sch;
    	}
    	Rect r = new Rect();
    	getDrawingRect(r);
  		int w = r.right - r.left;//canvas.getWidth();
   		int h = r.bottom - r.top;//canvas.getHeight() - 50;
        mPole = (w < h ? w : h);
        mPole >>= 3;
        
        Paint bila = new Paint();
        bila.setARGB(255, 200, 200, 200);
        Paint cerna = new Paint();
        cerna.setARGB(255, 100, 100, 100);
        Paint modra = new Paint();
        modra.setARGB(255, 0, 0, 255);
        Paint zelena = new Paint();
        zelena.setARGB(255, 0, 255, 0);
        Paint bilaf = new Paint();
        bilaf.setARGB(255, 255, 255, 255);
        Paint cernaf = new Paint();
        cernaf.setARGB(255, 0, 0, 0);
        boolean clovek = hrajeClovek();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
            	Paint p;
            	if (clovek && mox == i && moy == j) {
            		p = zelena;
            	} else
            	if (clovek && mcx == i && mcy == j) {
            		p = modra;
            	} else {
            		p = ((((i + j) & 1) == 1) ? bila : cerna);
            	}
            	int sx = (mOtoceno ? 7 - i : i) * mPole;
            	int sy = (mOtoceno ? j : 7 - j) * mPole;
                canvas.drawRect(new Rect(sx, sy, sx + mPole, sy + mPole), p);
                byte co = sch[Pozice.a1 + i + 10 * j];
                
                if (co != 0 && co > -7 && co < 7) {
                	Drawable dr = mFigury[co > 0 ? 1 : 0][co > 0 ? co -1 : -co - 1];
                	dr.setBounds(sx, sy, sx + mPole, sy + mPole);
                	dr.draw(canvas);
                }
            }
    }

	
	public void dlg(String co) {
		Dialog d = new Dialog(this.getContext());
		d.setTitle(co);
		d.show();
	}
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
    	if (isPremyslim()) return false;
    	switch (keyCode) {
    	case KeyEvent.KEYCODE_DPAD_UP:
    		if (!mOtoceno && mcy < 7 || mOtoceno && mcy > 0) {
    			if (mOtoceno) mcy--; else mcy++;
    			invalidate();
    		}
    		return true;
    	
    	case KeyEvent.KEYCODE_DPAD_DOWN:
    		if (mOtoceno && mcy < 7 || !mOtoceno && mcy > 0) {
    			if (mOtoceno) mcy++; else mcy--;
    			invalidate();
    		}
    		return true;
    	case KeyEvent.KEYCODE_DPAD_RIGHT:
    		if (!mOtoceno && mcx < 7 || mOtoceno && mcx > 0) {
    			if (mOtoceno) mcx--; else mcx++;
    			invalidate();
    		}
    		return true;
    	case KeyEvent.KEYCODE_DPAD_LEFT:
    		if (mOtoceno && mcx < 7 || !mOtoceno && mcx > 0) {
    			if (mOtoceno) mcx++; else mcx--;
    			invalidate();
    		}
    		return true;
    	case KeyEvent.KEYCODE_DPAD_CENTER:
    		if (!hrajeClovek()) return true;
    		Vector t = mPozice.nalezTahy();
    		int pole = Pozice.a1 + mcx + 10 * mcy;
    		if (mPozice.JeTam1(t, pole)) {
    			mox = mcx;
    			moy = mcy;
    			invalidate();
    			return true;
    		}
    		int pole1 = Pozice.a1 + mox + 10 * moy;
    		if (mPozice.JeTam2(t, pole1, pole)) {
    			int tah = mPozice.DoplnTah(t, pole1, pole);
    			tahni(tah);
    			return true;
    		}
    		
    		return true;
    	}
    	return false;
    }
    
    public boolean isPremyslim() {
    	return mPremyslim;
    }
    
    
    
    
    protected void tahniPrograme() {
    	mPremyslim = true;
    	System.arraycopy(mPozice.sch, 0, mSchPriMysleni, 0, Pozice.h8 + 1);
    	Thread t = new Thread() {
    		 public void run() {
    			 mPozice.nalezTahy();
    			 final int tah;
    			 tah = Minimax.minimax(mPozice, 5000); 
    			 mHandler.post(
    					 new Runnable() {

							public void run() {
								mPremyslim = false;
								if (tah != 0) 
									tahni(tah);
								else
									dlg("The end");
							}}
    					 );
    		 }
    	};
    	t.start();
     }
    
    protected void novaPartie() {
    	mPozice = new Pozice();
    	invalidate();
    }
    
    protected void hrajTed() {
    	if (mPozice.bily) {
    		mBilyClovek = false;
    		mCernyClovek = true;
    	} else {
    		mBilyClovek = true;
    		mCernyClovek = false;
    	}
    //	this.mcx = this.mcy = this.mcx = this.mcy  
    	invalidate();
    	tahniPrograme();
    }
    
}
