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

  
	public static final byte[] mZakladniPostaveni1 = {
		100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
		100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
	    //     a    b    c    d    e    f    g    h
	    100,   0,   0,   0,   0,   0,   0,   0,  -6, 100, // 1
	    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 2
	    100,   0,   0,   0,   0,   0,   6,   4,   0, 100, // 3
	    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 4
	    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 5
	    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 6
	    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 7
	    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, // 8
	    100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
	    100, 100, 100, 100, 100, 100, 100, 100, 100, 100
	};
 
	public static final byte[] mZakladniPostaveni = {
		100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
		100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
			// a    b    c    d    e    f    g    h
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

	public static final byte[] mOfsety = {
		1,  -1,  10,  -10, /* Vez*/
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