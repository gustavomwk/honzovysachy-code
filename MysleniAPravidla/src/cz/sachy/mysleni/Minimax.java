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

import cz.sachy.pravidla.Task;

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
	
	
	public static int alfabetaBrani(Task task, int hloubka, int alfa, int beta) {
		int h = HodnotaPozice.hodnotaPozice(task.board, alfa, beta);
		if (hloubka == 0) return h;
		if (h > MAT || h < -MAT) return h;
		boolean sach = task.board.sach();
		if (h > alfa && !sach) {
			alfa = h;
			if (h >= beta) {
				return beta; 
			}
		}
		
		if (sach) 
			task.nalezPseudolegalniTahyZasobnik();
		else
			task.nalezPseudolegalniBraniZasobnik();
		int odkud = task.getOdkud();
		int kam = task.getKam();
		
		if (kam - odkud == 0) {
			task.mZasobnikTahu.pos--;
			if (task.board.sach()) return -MAT;
			return alfa;
		}
		hloubka--;
		
		for (int i = odkud; i < kam; i++) {
			int t = task.mZasobnikTahu.tahy[i];

			task.tahni(t, false, false, null);
			h = dalOdMatu(alfabetaBrani(task, hloubka, blizKMatu(beta), blizKMatu(alfa)));
			task.tahniZpet(t, false, null);

			if (h > alfa) {
				alfa = h;
				if (h >= beta) {
					task.mZasobnikTahu.pos--;
					return beta; 
				}
			}
		}
		task.mZasobnikTahu.pos--;
		return alfa;
	}

	public static int alfabeta(Task task, int hloubka, int alfa, int beta) {
		if (System.currentTimeMillis() > task.mTimeStart) {
			task.mExitThinking = true;
			return alfa;
		}
		if (hloubka == 0) return alfabetaBrani(task, 4, alfa, beta);
		int h = HodnotaPozice.hodnotaPozice(task.board, alfa, beta);
		if (h > MAT || h < -MAT) return h;
		task.nalezPseudolegalniTahyZasobnik();
		int odkud = task.getOdkud();
		int kam = task.getKam();
		
		if (kam - odkud == 0) {
			task.mZasobnikTahu.pos--;
			if (task.board.sach()) return -MAT;
			return 0;
		}
		hloubka--;
		
		for (int i = odkud; i < kam; i++) {
			int t = task.mZasobnikTahu.tahy[i];
			//int hf = p.hashF.hash(p);
			task.tahni(t, false, false, null);
			h = dalOdMatu(alfabeta(task, hloubka, blizKMatu(beta), blizKMatu(alfa)));
			task.tahniZpet(t, false, null);
			//int hg = p.hashF.hash(p);
			//if (hg != hf) {
			//	System.out.println(p.toString() + "\n" + p.tah2Str(t));
			//	throw new RuntimeException("Jauvajs");
			//}
			if (task.mExitThinking) {
				task.mZasobnikTahu.pos--;
				return alfa;
			}
			if (h > alfa) {
				alfa = h;
				if (h >= beta) {
					task.mZasobnikTahu.pos--;
					return beta; 
				}
			}
		}
		task.mZasobnikTahu.pos--;
		return alfa;
	}
	
	public static int minimax(Task task, long casMs) {
		task.mTimeStart = System.currentTimeMillis() + casMs;
		task.mExitThinking = false;
		task.nalezTahyZasobnik();
		
		int odkud = task.getOdkud();
		int kam = task.getKam();
		if (kam - odkud == 0) {
			task.mZasobnikTahu.pos--;
			return 0;
		}
		switch (kam - odkud) {
		case 0: task.mZasobnikTahu.pos--;
			if (task.board.sach()) return -MAT;
			return 0;
		case 1: task.mZasobnikTahu.pos--;
			return task.mZasobnikTahu.tahy[odkud];
		}
		for (int hloubka = 0; hloubka < 10; hloubka++) {
			int max = -MAT + 1;
			for (int i = odkud; i < kam; i++) {
				int t = task.mZasobnikTahu.tahy[i];
				int hf = task.mHashF.hash(task.board);
				task.tahni(t, false, false, null);
				int h = dalOdMatu(alfabeta(task, hloubka, blizKMatu(MAT), blizKMatu(max)));
				
				if (!task.mExitThinking && (i == 0 || h > max)) {
					max = h;
					if (i != 0)
					{
						for (int j = i; j > odkud; j--)
							task.mZasobnikTahu.tahy[j] = task.mZasobnikTahu.tahy[j - 1];
						task.mZasobnikTahu.tahy[odkud] = t;
					}
				}
				task.tahniZpet(t, false, null);
				int hg = task.mHashF.hash(task.board);
				if (hg != hf) {
					throw new RuntimeException("Error, board has changed!!!");
				}
				if (task.mExitThinking) break;
			}
			if (task.mExitThinking) break;
			//System.out.println(hloubka + " " + Task.tah2StrNoBoard(task.mZasobnikTahu.tahy[odkud]));
		}
		task.mZasobnikTahu.pos--;
		return task.mZasobnikTahu.tahy[odkud];
	}
}
