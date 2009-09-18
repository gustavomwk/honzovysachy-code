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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AktivitaSachovnice extends Activity implements MenuItem.OnMenuItemClickListener {
	SachoveView mView;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    super.onCreateOptionsMenu(menu);
	    menu.add(Menu.NONE, 1, Menu.NONE, S.g("FLIP_BOARD")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 2, Menu.NONE, S.g("MOVE")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 3, Menu.NONE, S.g("NEW_GAME")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 4, Menu.NONE, S.g("UNDO")).setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 5, Menu.NONE, S.g("REDO")).setOnMenuItemClickListener(this);
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
        S.init("en");
        mView = new SachoveView(this);
        setContentView(mView);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	mView.replayPromotion(resultCode);
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
		}
		return true;
	}
}