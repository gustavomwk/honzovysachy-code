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

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AktivitaSachovnice extends Activity implements MenuItem.OnMenuItemClickListener {
	SachoveView mView;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    super.onCreateOptionsMenu(menu);
	    menu.add(Menu.NONE, 1, Menu.NONE, "Flip board").setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 2, Menu.NONE, "Move").setOnMenuItemClickListener(this);
	    menu.add(Menu.NONE, 3, Menu.NONE, "New game").setOnMenuItemClickListener(this);
	    return true;
	}
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new SachoveView(this);
        setContentView(mView);
    }


	public boolean onMenuItemClick(MenuItem item) {
		if (mView.isPremyslim()) {
			mView.dlg("thinking...");
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
		}
		return true;
	}
}