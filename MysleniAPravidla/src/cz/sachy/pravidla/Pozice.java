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

package cz.sachy.pravidla;

import java.util.Vector;


public class Pozice {

	public static final int a1 = 21;
	public static final int b1 = 22;
	public static final int c1 = 23;
	public static final int d1 = 24;
	public static final int e1 = 25;
	 public static final int f1 = 26;
	 public static final int g1 = 27;
	  public static final int h1 = 28;
	  public static final int a2 = 31;
	  public static final int b2 = 32;
	  public static final int c2 = 33;
	  public static final int d2 = 34;
	  public static final int e2 = 35;
	  public static final int f2 = 36;
	  public static final int g2 = 37;
	  public static final int h2 = 38;
	  public static final int a3 = 41;
	  public static final int b3 = 42;
	  public static final int c3 = 43;
	  public static final int d3 = 44;
	  public static final int e3 = 45;
	  public static final int f3 = 46;
	  public static final int g3 = 47;
	  public static final int h3 = 48;
	  public static final int a4 = 51;
	  public static final int b4 = 52;
	  public static final int c4 = 53;
	  public static final int d4 = 54;
	  public static final int e4 = 55;
	  public static final int f4 = 56;
	  public static final int g4 = 57;
	  public static final int h4 = 58;
	  public static final int a5 = 61;
	  public static final int b5 = 62;
	  public static final int c5 = 63;
	  public static final int d5 = 64;
	  public static final int e5 = 65;
	  public static final int f5 = 66;
	  public static final int g5 = 67;
	  public static final int h5 = 68;
	  public static final int a6 = 71;
	  public static final int b6 = 72;
	  public static final int c6 = 73;
	  public static final int d6 = 74;
	  public static final int e6 = 75;
	  public static final int f6 = 76;
	  public static final int g6 = 77;
	  public static final int h6 = 78;
	  public static final int a7 = 81;
	  public static final int b7 = 82;
	  public static final int c7 = 83;
	  public static final int d7 = 84;
	  public static final int e7 = 85;
	  public static final int f7 = 86;
	  public static final int g7 = 87;
	  public static final int h7 = 88;
	  public static final int a8 = 91;
	  public static final int b8 = 92;
	  public static final int c8 = 93;
	  public static final int d8 = 94;
	  public static final int e8 = 95;
	  public static final int f8 = 96;
	  public static final int g8 = 97;
	  public static final int h8 = 98;

  
	public static final byte[] mZakladniPostaveni2 = {
	    100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
	    100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
	    //     a    b    c    d    e    f    g    h
	    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 1
	    100,   0,   0,   1,   0,   0,   0,   0,   0, 100, // 2
	    100,   0,   1,   0,   1,   0,   6,   1,   1, 100, // 3
	    100,   1,   0,   0,  -1,   0,   0,   0,   0, 100, // 4
	    100,  -1,   0,  -1,   0,   0,   0,   0,  -1, 100, // 5
	    100,   0,   0,  -1,   0,   0,  -6,   0,   0, 100, // 6
	    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 7
	    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 8
	    100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
	    100, 100, 100, 100, 100, 100, 100, 100, 100, 100
	  };
 
  public static final byte[] mZakladniPostaveni = {
    100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
    100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
    //     a    b    c    d    e    f    g    h
    100,   4,   2,   3,   5,   6,   3,   2,   4, 100, // 1
    100,   1,   1,   1,   1,   1,   1,   1,   1, 100, // 2
    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 3
    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 4
    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 5
    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 6
    100,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1, 100, // 7
    100,  -4,  -2,  -3,  -5,  -6,  -3,  -2,  -4, 100, // 8
    100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
    100, 100, 100, 100, 100, 100, 100, 100, 100, 100
  };

  public static final byte[] mOfsety =
  {  1,  -1,  10,  -10, /* Vez*/
    11, -11,   9,  -9,  /* Strelec*/
    21,  19,  12,   8, -21, -19, -12, - 8 /* Kun*/
  };
 
  
  public byte roch;  /* binarne 00...00vmVM */
  /* V,v - moznost velke rosady bileho a cerneho
     M,m - totez s malou  */
  public byte mimoch;  /* Pole, na nemz stoji pesec tahnuvsi v predchozim tahu o 2,
    nebo 0 pokud se minule netahlo pescem o 2 */
  public boolean bily;    /* Je bily na tahu ?*/

  public byte[] sch;
    
  protected Object clone() {
	return new Pozice(this);
  }
  
  public Pozice(Pozice p) {
	sch = new byte[mZakladniPostaveni.length];
	System.arraycopy(p.sch, 0, sch, 0, sch.length);
	bily = p.bily;
	roch = p.roch;
	mimoch = p.mimoch;
  }
  
  public boolean equals(Object o) {
	  if (o == null) return false;
	  if (o.getClass() != getClass()) return false;
	  Pozice p = (Pozice) o;
	  if (bily != p.bily || roch != p.roch || mimoch != p.mimoch) return false;
	  if (p.sch.length != sch.length) return false;
	  for (int i = 0; i < sch.length; i++)
		  if (sch[i] != p.sch[i]) return false;
      return true;
  }
  
  public Pozice() {
    sch = new byte[mZakladniPostaveni.length];
    System.arraycopy(mZakladniPostaveni, 0, sch, 0, sch.length);
    bily = true;
    roch = 15;
    mimoch = 0;
  }
   
  private void zaradTah(Vector tahy, int i, int j) {
    tahy.add(new Integer((i << 7) | j));
  }
  
  private void zaradMimochodem(Vector tahy, int i, int j) {
    tahy.add(new Integer( (1<<15) | ((i)<<7) | (j)));
  }
  
  private void zaradBilouPromenu(Vector tahy, int p1, int p2, int fig) {
    tahy.add(new Integer((3<<14)|(fig<<10)|((p1-a7)<<7)|((p2-a8)<<4)));
  }
  
  private void zaradCernouPromenu(Vector tahy, int p1, int p2, int fig) {
    tahy.add(new Integer((13<<12)|(fig<<10)|((p1-a2)<<7)|((p2-a1)<<4)));
  }
  

  private void dlouhaBilaFigura(Vector tahy, int o1, int o2, int p) {
    for(int j = o1; j <= o2; j++) {
      for(int q = p + mOfsety[j]; sch[q] <= 0; q += mOfsety[j]) {
        zaradTah(tahy, p, q);
        if (sch[q] < 0) {
          break;
        }
      }
    }
  }
  
  private void dlouhaCernaFigura(Vector tahy, int o1, int o2, int p) {
    for(int j = o1; j <= o2; j++) {
      for(int q = p + mOfsety[j]; sch[q] >= 0 && sch[q] < 7; q += mOfsety[j]) {
        zaradTah(tahy, p, q);
        if (sch[q] > 0) {
          break;
        }
      }
    }
  }
  
  private void zaradRosadu(Vector tahy, int jakou) {
    tahy.add(new Integer(jakou));
  }
  
  public boolean ohrozeno(int i, boolean bilym) /*bilym - ohrozuje to pole bily*/
   {
    int j,k;
    if (bilym) {
      if (sch[i - 9] == 1) return true;/*pescem*/
      if (sch[i - 11] == 1) return true;/*pescem*/
      for (j = 8; j <= 15; j++)
        if (sch[i + mOfsety[j]] == 2) return true;/*konem*/
      for(j = 0; j <= 7; j++)
        if (sch[i + mOfsety[j]] == 6) return true;/*kralem*/
      for (j = 0; j <= 3; j++) /*vezi nebo damou po rade/sloupci*/
       {
        k = mOfsety[j];
        while(true) {
          if ((sch[i + k] == 4) || sch[i + k] == 5)
            return true;
          if (sch[i + k] != 0) break;
          k+=mOfsety[j];
        }
      }
      for(j = 4; j <= 7; j++) /*strelcem nebo damou po diagonale*/
       {
        k = mOfsety[j];
        while (true) {
          if ((sch[i + k] == 3) || (sch[i + k] == 5)) return true;
          if (sch[i + k] != 0) break;
          k+=mOfsety[j];
        }
      }
    } else /*cernym*/
    {
      if (sch[i + 9] == -1) return true;/*pescem*/
      if (sch[i + 11] == -1) return true;/*pescem*/
      for(j = 8; j <= 15; j++)
        if (sch[i + mOfsety[j]] == -2) return true;/*konem*/
      for (j = 0; j <= 7; j++)
        if (sch[i + mOfsety[j]] == -6) return true;/*kralem*/
        for (j = 0; j <= 3; j++) /*vezi nebo damou po rade/sloupci*/
         {
          k = mOfsety[j];
          while (true) {
            if (sch[i + k] == -4 ||
                sch[i + k] == -5) return true;
            if (sch[i + k] != 0) break;
            k+=mOfsety[j];
          }
        }
        for (j = 4; j <= 7; j++) /*vezi nebo damou po rade/sloupci*/
        {
         k = mOfsety[j];
         while (true) {
           if (sch[i + k] == -3 ||
               sch[i + k] == -5) return true;
           if (sch[i + k] != 0) break;
           k+=mOfsety[j];
         }
       }
    }
    return false;
  }

  public boolean sach(boolean bilemu) {
    int kral = (bilemu ? 6 : -6);
    for (int i = a1; i <= h8; i++) {
      if (sch[i] == kral) {
        return ohrozeno(i, !bilemu);
      }
    }
    return false;
  }
  
  public boolean sach() {
    return sach(bily);
  }
  
  
  public Vector nalezPseudolegalniTahy2() {
    int j, i; /*promenne pro for cykly*/
    Vector tahy = new Vector();
    
    if (bily) {
     for (i = a1; i <= h8; i++)
      {if (sch[i] < 1 || sch[i] > 6) continue;
       switch (sch[i]) {
        case 1 : /*pesec*/
         if (i < a7) /*tedy nehrozi promena*/ {
           if (sch[i + 11] < 0) zaradTah(tahy, i, i + 11);
           if (sch[i + 9] < 0) zaradTah(tahy, i, i + 9);
           if (sch[i + 10] == 0) {
             zaradTah(tahy, i, i + 10);
             if (i <= h2 && sch[i + 20] == 0) zaradTah(tahy, i,i+20);
           } /* pescem o 2*/
           if (mimoch == i + 1) zaradMimochodem(tahy, i, i + 11); else
           if (mimoch == i - 1) zaradMimochodem(tahy, i, i + 9);
         }
        else /* i>=a7 => promeny pesce*/
         {if (sch[i + 10] == 0) for(j=3;j>=0;j--) zaradBilouPromenu(tahy, i, i + 10, j);
          if (sch[i + 11] < 0) for(j=3;j>=0;j--) zaradBilouPromenu(tahy, i, i + 11, j);
          if (sch[i + 9] < 0) for(j=3;j>=0;j--) zaradBilouPromenu(tahy, i, i + 9, j);
         }
      break;
     case 2: /* kun*/
      for (j = 8; j <= 15; j++)
        if ((sch[i + mOfsety[j]]) <=0 ) zaradTah(tahy, i,i + mOfsety[j]);
      break;
     case 3: /* strelec*/
       dlouhaBilaFigura(tahy, 4, 7, i);
     break;
     case 4: /* vez*/
       dlouhaBilaFigura(tahy, 0, 3, i);
     break;
     case 5: /* dama*/
       dlouhaBilaFigura(tahy, 0, 7, i);
     break;
     case 6: /* kral*/
       for (j = 0; j <= 7; j++) 
         if ((sch[i + mOfsety[j]]) <= 0) zaradTah(tahy, i, i + mOfsety[j]);
       if (i == e1 && ((roch & 1) != 0) && (sch[i + 1] == 0) && (sch[i + 2] == 0) && (sch[h1] == 4)
         && !ohrozeno(i + 1, false) && !ohrozeno(i, false))  {
         zaradRosadu(tahy, Task.MBRoch);
       }
       if (i == e1 && ((roch & 2) != 0) && (sch[i - 1] == 0) && (sch[i - 2] == 0) && (sch[a1] == 4)
         && !ohrozeno(i - 1, false) && !ohrozeno(i, false))  {
         zaradRosadu(tahy, Task.VBRoch);
       }
       break; /* od krale */
     }/* od switch*/
   } /* od for cyklu*/
 } /* od hraje bily*/
    else
     {
     for (i = a1; i <= h8; i++) {
       if (sch[i] >=0 ) continue;
       switch (sch[i]) {
         case -1 : /*pesec*/
         if (i>h2) /*tedy nehrozi promena*/ {
           if (sch[i - 11] > 0 && sch[i - 11] < 7)
             zaradTah(tahy, i, i - 11);
           if (sch[i - 9] > 0 && sch[i - 9] < 7) 
             zaradTah(tahy, i, i - 9);
           if (sch[i - 10] == 0) /* policko pred pescem je volne*/ {
             zaradTah(tahy, i, i - 10);
             if (i >= a7 && sch[i - 20] == 0)
               zaradTah(tahy, i, i-20);
             } /* pescem o 2*/
             if (mimoch == i + 1) zaradMimochodem(tahy, i, i - 9); else
             if (mimoch == i - 1) zaradMimochodem(tahy, i, i - 11);
           } else /* i<=h2 => promeny pesce*/ {
             if (sch[i - 10] == 0)
               for(j = 3; j >= 0; j--)
                 zaradCernouPromenu(tahy, i, i - 10, j);
             if (sch[i - 11] > 0 && sch[i - 11] < 7)
               for(j = 3; j >= 0; j--)
                 zaradCernouPromenu(tahy, i, i - 11, j);
             if (sch[i - 9] > 0 && sch[i - 9] < 7)
               for(j = 3; j >= 0; j--)
                 zaradCernouPromenu(tahy, i, i - 9, j);
           }
      break;
     case -2: /* kun*/
      for (j = 8; j <= 15; j++)
        if (sch[i + mOfsety[j]] >=0 && sch[i + mOfsety[j]]  <7)
          zaradTah(tahy, i, i + mOfsety[j]);
      break;
     case -3: /* strelec*/
       dlouhaCernaFigura(tahy, 4, 7, i);
       break;
     case -4: /* vez*/
       dlouhaCernaFigura(tahy, 0, 3, i);
       break;
     case -5: /* dama*/
       dlouhaCernaFigura(tahy, 0, 7, i);
       break;
     case -6: /* kral*/
       for (j = 0; j <= 7; j++)
         if (sch[i + mOfsety[j]] >= 0 && sch[i + mOfsety[j]] < 7)
           zaradTah(tahy, i, i + mOfsety[j]);
       if (i == e8 && (roch & 4) != 0 && sch[f8] == 0 && sch[g8] == 0  && (sch[h8] == -4)
           && !ohrozeno(e8, true) && !ohrozeno(f8, true)) {
         zaradRosadu(tahy, Task.MCRoch);
       }
       if (i == e8 && (roch & 8) != 0 && sch[d8] == 0 && sch[c8] == 0  && (sch[a8] == -4)
         && !ohrozeno(e8, true) && !ohrozeno(d8, true)){
       zaradRosadu(tahy, Task.VCRoch);
      }
      break;
     }/* od switch*/
    } /* od for cyklu*/
    } /* od hraje cerny*/
    return tahy;
  }
  
  


  
 
	public String toString() {
		StringBuffer s = new StringBuffer();
		for (int j = 7; j >= 0; j--) {
			for (int i = 0; i <= 7; i++) {
			char c = ' ';
			switch (sch[Pozice.a1 + i + j * 10]) {
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
			s.append(c);
		}
		s.append('\n');
		}
		return new String(s);
	}

} 