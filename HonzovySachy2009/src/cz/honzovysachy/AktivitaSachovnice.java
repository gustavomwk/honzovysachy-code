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

package cz.honzovysachy;

import cz.honzovysachy.resouces.S;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AktivitaSachovnice extends Activity implements MenuItem.OnMenuItemClickListener {
	public static boolean mChangedLanguage; 
	
	
	public static final String SETTINGS = "settings";
	public static final String LOCALE = "locale";
	public static final String LOCALE_FILE = "/sdcard/strings_hs.txt";
	
	SachoveView mView;
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		if (!mChangedLanguage) return true;
		mChangedLanguage = false;
		menu.clear();
	    menu.add(Menu.NONE, 1, Menu.NONE, S.g("FLIP_BOARD")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 2, Menu.NONE, S.g("MOVE")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 3, Menu.NONE, S.g("NEW_GAME")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 4, Menu.NONE, S.g("UNDO")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 5, Menu.NONE, S.g("REDO")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 6, Menu.NONE, S.g("SAVE_GAME")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 7, Menu.NONE, S.g("HUMAN_OPONENT")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 8, Menu.NONE, S.g("SETTINGS")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 9, Menu.NONE, S.g("ABOUT")).setOnMenuItemClickListener(this);
	    return true;
	}
	
	@Override
    protected void onSaveInstanceState(Bundle outState) {
		
	}
	
    @Override
    protected void onStop() {
       super.onStop();
       mView.trySave();
    }
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getBaseContext().getSharedPreferences(SETTINGS, 0);
        int i = pref.getInt(LOCALE, 1);
        S.init(i, LOCALE_FILE);
        this.setTitle(S.g("HONZOVY_SACHY"));
        mChangedLanguage = true;
        mView = new SachoveView(this);
        setContentView(mView);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode < 10) {
    		mView.replayPromotion(resultCode);
    		return;
    	}
    	mView.save(data);
    }

	public boolean onMenuItemClick(MenuItem item) {
		if (mView.isPremyslim()) {
			mView.dlg(S.g("THINKING"));
			return true;
		}
		switch (item.getItemId()) {
		case 1: mView.otoc();
			break;
		case 2: 
			mView.hrajTed();
			break;
		case 3: 
			mView.novaPartie();
			break;
		case 4: 
			mView.undo();
			break;
		case 5: 
			mView.redo();
			break;
		case 6: 
			mView.save();
			break;
		case 7: 
			mView.hh();
			break;
		case 8: 
			mView.settings();
			break;
		case 9:
			Intent myIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("http://honzovysachy.sf.net"));
			startActivity(myIntent);
			break;
		}
		return true;
	}
}