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

package cz.sachy.mysleni;

import cz.sachy.pravidla.Pozice;

public class HodnotaPozice {
	public static final int[] mStdCenyFigur = {0, 50, 150, 150, 250, 450, Minimax.BLIZKO_MATU};
	public static int hodnotaPozice(Pozice p) {
		p.mOh++;
		int suma = 0;
		for (int i = Pozice.a1; i <= Pozice.h8; i++) {
			switch (p.sch[i]) {
			case 0: break;
			case 100: break;
			default:
				int f = p.sch[i];
				if (f > 0) { 
					suma += mStdCenyFigur[f];
				} else {
					suma -= mStdCenyFigur[-f];
				}
			}
		}
		return p.bily ? suma : -suma;
	}
}
