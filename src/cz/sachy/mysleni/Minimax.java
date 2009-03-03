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

import java.util.Vector;

import cz.sachy.pravidla.Pozice;

public class Minimax {
	public static final int MAT = 30000;
	public static final int BLIZKO_MATU = 20000;
	private static int dalOdMatu(int i) {
		if (i < BLIZKO_MATU && i > -BLIZKO_MATU) return -i;
		if (i > 0) return -(i - 1);
		return -(i + 1);
	}
	private static int blizKMatu(int i) {
		if (i < BLIZKO_MATU && i > -BLIZKO_MATU) return -i;
		if (i > 0) return -(1 + i);
		return -(i - 1);
	}
	public static int alfabeta(Pozice p, int hloubka, int alfa, int beta) {
		if (hloubka == 0) return HodnotaPozice.hodnotaPozice(p); 
		Vector tahy = p.nalezTahy();
		if (tahy.size() == 0) {
			if (p.sach()) return -MAT;
			return 0;
		}
		hloubka--;
		for (int i = 0; i < tahy.size(); i++) {
			int t = ((Integer)(tahy.elementAt(i))).intValue();
			p.tahni(t, false, false, null);
			int h = dalOdMatu(alfabeta(p, hloubka, blizKMatu(beta), blizKMatu(alfa)));
			p.tahniZpet(t, false, null);
			if (h > alfa) {
				alfa = h;
				if (h > beta) return beta; 
			}
		}
		return alfa;
	}

	public static int minimax(Pozice p) {
		Vector tahy = p.nalezTahy();
		switch (tahy.size()) {
		case 0: return 0;
		case 1: return ((Integer)(tahy.elementAt(0))).intValue();
		}
		int maxT = 0;
		int max = -MAT + 1;
		for (int i = 0; i < tahy.size(); i++) {
			int t = ((Integer)(tahy.elementAt(i))).intValue();
			p.tahni(t, false, false, null);
			int h = dalOdMatu(alfabeta(p, 2, blizKMatu(MAT), blizKMatu(max)));
			if (i == 0 || h > max) {
				max = h;
				maxT = t;
			}
			p.tahniZpet(t, false, null);
		}
		return maxT;
	}
}
