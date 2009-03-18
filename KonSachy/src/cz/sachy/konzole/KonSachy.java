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

package cz.sachy.konzole;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import cz.sachy.mysleni.Minimax;
import cz.sachy.pravidla.Pozice;
import cz.sachy.pravidla.ZasobnikStruct;

public class KonSachy {
	private static void hlavniDosCyklus() {
		Pozice p = new Pozice();
		BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));
		hlavni:
		while (true) {
			String s = "";
			try {
				s = br.readLine();
			} catch (IOException e) {
				System.out.println("Koncim");
				break;
			}
			//if (s.length() > 2)	s = s.substring(0, 2);
			if (s.equals("?")) {
				System.out.println(
						"? - tahle napoveda\n" +
						"ta - tahni\n" +
						"tz - tahni zpet\n" +
						"tg - test generatoru\n" +
						"tb - test brani\n" +
						"sa - tiskni sachovnici\n" +
						"bb - alfa beta brani\n" +
						"ko - konec\n");
				continue;
			}
			if (s.equals("ta")) {
				Vector t = p.nalezTahy();
				if (t.size() == 0) {
					System.out.println("Neni tah");
					continue;
				}
				int tah = Minimax.minimax(p);
				System.out.println(p.tah2Str(t, tah));
				p.tahni(tah, true, true, null);
				tiskniSachovnici(p);
				continue;
			}
			if (s.equals("tz")) {
				if (p.mIndexVPartii >= 0)
				p.tahniZpet(((ZasobnikStruct)(p.mPartie.elementAt(p.mIndexVPartii))).tah, true, null);
					tiskniSachovnici(p);
				continue;
			}
			if (s.equals("tg")) {
				Vector t = p.nalezTahy();
				for (int i = 0; i < t.size(); i++) {
					System.out.print(p.tah2Str(t, ((Integer)(t.elementAt(i))).intValue()) + " ");
				}
				System.out.println();
				continue;
			}
			if (s.equals("tb")) {
				p.nalezPseudolegalniBraniZasobnik();
				int odkud = p.getOdkud();
				int kam = p.getKam();
				for (int i = odkud; i < kam; i++) {
					System.out.print(p.tah2Str(new Vector(), p.mZasobnik.tahy[i]) + " (" + p.mZasobnik.hodnoty[i] + ") ");
				}
				p.mZasobnik.pos--;
				System.out.println();
				continue;
			}
			if (s.equals("bb")) {
				System.out.println(Minimax.alfabetaBrani(p, 4, -Minimax.MAT, Minimax.MAT));
				continue;
			}
			if (s.equals("sa")) {
				tiskniSachovnici(p);
				continue;
			}
			if (s.equals("ko")) {
				System.out.println("Koncim");
				break;
			}
			Vector t = p.nalezTahy();
			for (int i = 0; i < t.size(); i++) {
				int tah = ((Integer)(t.elementAt(i))).intValue();
				if (s.equals(p.tah2Str(t, tah))) {
					p.tahni(tah, true, true, null);
					tiskniSachovnici(p);
					continue hlavni;
				}
				
			}
			System.out.println("Neznamy prikaz nebo neplatny tah");
		}
	}
	public static void tiskniSachovnici(Pozice p) {
		for (int i = Pozice.a1; i <= Pozice.h8; i++) {
			char c = ' ';
			switch (p.sch[i]) {
			case 100:
				System.out.println("");
				i++;
				continue;
			case 1:
				c = 'P';
				break;
			case -1:
				c = 'p';
				break;
			case 2:
				c = 'J';
				break;
			case -2:
				c = 'j';
				break;
			case 3:
				c = 'S';
				break;
			case -3:
				c = 's';
				break;
			case 4:
				c = 'V';
				break;
			case -4:
				c = 'v';
				break;
			case 5:
				c = 'D';
				break;
			case -5:
				c = 'd';
				break;
			case 6:
				c = 'K';
				break;
			case -6:
				c = 'k';
				break;
			}
			
			System.out.print(c);
		}
		System.out.println("");
	}
	public static void main(String[] args) {
		System.out.println("Honzuv sachovy program, '?' je napoveda");
		hlavniDosCyklus();
	}

}
