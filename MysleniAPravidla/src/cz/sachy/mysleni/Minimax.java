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
	
	
	public static int alfabetaBrani(Pozice p, int hloubka, int alfa, int beta) {
		int h = HodnotaPozice.hodnotaPozice(p, alfa, beta);
		if (hloubka == 0) return h;
		boolean sach = p.sach();
		if (h > alfa && !sach) {
			alfa = h;
			if (h >= beta) {
				return beta; 
			}
		}
		
		if (sach) 
			p.nalezPseudolegalniTahyZasobnik();
		else
			p.nalezPseudolegalniBraniZasobnik();
		int odkud = p.getOdkud();
		int kam = p.getKam();
		
		if (kam - odkud == 0) {
			p.mZasobnik.pos--;
			if (p.sach()) return -MAT;
			return alfa;
		}
		hloubka--;
		
		for (int i = odkud; i < kam; i++) {
			int t = p.mZasobnik.tahy[i];
			p.tahni(t, false, false, null);
			h = dalOdMatu(alfabetaBrani(p, hloubka, blizKMatu(beta), blizKMatu(alfa)));
			p.tahniZpet(t, false, null);
			if (h > alfa) {
				alfa = h;
				if (h >= beta) {
					p.mZasobnik.pos--;
					return beta; 
				}
			}
		}
		p.mZasobnik.pos--;
		return alfa;
	}

	public static int alfabeta(Pozice p, int hloubka, int alfa, int beta) {
		if (hloubka == 0) return alfabetaBrani(p, 4, alfa, beta);
		p.nalezPseudolegalniTahyZasobnik();
		int odkud = p.getOdkud();
		int kam = p.getKam();
		
		if (kam - odkud == 0) {
			p.mZasobnik.pos--;
			if (p.sach()) return -MAT;
			return 0;
		}
		hloubka--;
		
		for (int i = odkud; i < kam; i++) {
			int t = p.mZasobnik.tahy[i];
			p.tahni(t, false, false, null);
			int h = dalOdMatu(alfabeta(p, hloubka, blizKMatu(beta), blizKMatu(alfa)));
			p.tahniZpet(t, false, null);
			if (h > alfa) {
				alfa = h;
				if (h >= beta) {
					p.mZasobnik.pos--;
					return beta; 
				}
			}
		}
		p.mZasobnik.pos--;
		return alfa;
	}
	
	public static int minimax(Pozice p) {
		p.mOh = 0;
		p.nalezTahyZasobnik();
		int odkud = p.getOdkud();
		int kam = p.getKam();
		if (kam - odkud == 0) {
			p.mZasobnik.pos--;
			return 0;
		}
		switch (kam - odkud) {
		case 0: p.mZasobnik.pos--;
			if (p.sach()) return -MAT;
			return 0;
		case 1: p.mZasobnik.pos--;
			return p.mZasobnik.tahy[odkud];
		}
		for (int hloubka = 0; hloubka < 2; hloubka++) {
			int max = -MAT + 1;
			for (int i = odkud; i < kam; i++) {
				int t = p.mZasobnik.tahy[i];
				p.tahni(t, false, false, null);
				int h = dalOdMatu(alfabeta(p, hloubka, blizKMatu(MAT), blizKMatu(max)));
				if (i == 0 || h > max) {
					max = h;
					if (i != 0)
					{
						for (int j = i; j > odkud; j--)
							p.mZasobnik.tahy[j] = p.mZasobnik.tahy[j - 1];
						p.mZasobnik.tahy[odkud] = t;
					}
					
				}
				p.tahniZpet(t, false, null);
			}
		}
		p.mZasobnik.pos--;
		return p.mZasobnik.tahy[odkud];
	}
}
