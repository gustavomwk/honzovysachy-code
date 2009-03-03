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

import java.util.Iterator;
import java.util.Vector;

public class Pozice {
  

  public static final byte[] ofsety =
  {  1,  -1,  10,  -10, /* Vez*/
    11, -11,   9,  -9,  /* Strelec*/
    21,  19,  12,   8, -21, -19, -12, - 8 /* Kun*/
  };
  
  
  public static final byte[] zakladniPostaveni = {
    100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
    100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
    /*     a    b    c    d    e    f    g    h*/
    100,   4,   2,   3,   5,   6,   3,   2,   4, 100, /* 1 */
    100,   1,   1,   1,   1,   1,   1,   1,   1, 100, /* 2*/
    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, /* 3*/
    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, /* 4*/
    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, /* 5*/
    100,   0,   0,   0,   0,   0,   0,   0,   0, 100, /* 6*/
    100,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1, 100, /* 7*/
    100,  -4,  -2,  -3,  -5,  -6,  -3,  -2,  -4, 100, /* 8*/
    100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
    100, 100, 100, 100, 100, 100, 100, 100, 100, 100
  };
  
  public byte roch;  /* binarne 00...00vmVM */
  /* V,v - moznost velke rosady bileho a cerneho
     M,m - totez s malou  */
  public byte mimoch;  /* Pole, na nemz stoji pesec tahnuvsi v predchozim tahu o 2,
    nebo 0 pokud se minule netahlo pescem o 2 */
  public boolean bily;    /* Je bily na tahu ?*/

  public byte[] sch;
  
  Vector mtahy;
  
  public Vector partie;
  
  public int indexVPartii;
  
  public Vector zasobnik;
  
  public int indexVZasobniku;
    
  public Pozice() {
    sch = new byte[zakladniPostaveni.length];
    System.arraycopy(zakladniPostaveni, 0, sch, 0, sch.length);
    bily = true;
    roch = 15;
    mimoch = 0;
    indexVPartii = -1;
    indexVZasobniku = -1;
    partie = new Vector();
    zasobnik = new Vector();
    
    mtahy = nalezPseudolegalniTahy2();
  }
  
  protected void push(byte brani,boolean globalne) {
    if (globalne) {
      ((ZasobnikStruct)partie.elementAt(indexVPartii)).brani = brani;
    } else {
      ((ZasobnikStruct)zasobnik.elementAt(indexVZasobniku)).brani = brani;
    }
  }
  
  protected void push(boolean globalne, boolean ukousniKonec, int tah) {
    if (globalne) {
      indexVPartii++;
      if (indexVPartii <= partie.size()) {
        partie.add(new ZasobnikStruct(roch, mimoch, tah));
      } else {
        ((ZasobnikStruct) partie.elementAt(indexVPartii)).set(roch, mimoch, tah);
        if (ukousniKonec && partie.size() > indexVPartii + 1) {
          partie.setSize(indexVPartii + 1);
        }
      }
    } else {
      indexVZasobniku++;
      if (indexVZasobniku >= zasobnik.size()) {
        zasobnik.add(new ZasobnikStruct(roch, mimoch, tah));
      } else {
        ((ZasobnikStruct) zasobnik.elementAt(indexVZasobniku)).set(roch, mimoch, tah);
      }
    }
  }
  
  public void tahniZpet(int tah, boolean globalne, ZobrazPole zobrazPole) {
    
   int odkud, kam;
   ZasobnikStruct z;
   if (globalne) {
     z = (ZasobnikStruct) partie.elementAt(indexVPartii--);
   } else {
     z = (ZasobnikStruct) zasobnik.elementAt(indexVZasobniku--);
   }
   mimoch = z.mimoch;
   roch = z.roch;
   bily = !bily;
   
   if((tah >> 15) == 0) /* Normalni tah*/
    {
     kam = tah & 127;
     odkud = tah >> 7;
     sch[odkud] = sch[kam];
     sch[kam] = z.brani;
     if (zobrazPole != null) {
       zobrazPole.zobrazPole(odkud);
       zobrazPole.zobrazPole(kam);
     }
     return;
  }

   /* Nenormalni tah

      Mala bila rosada*/
   if (tah == MBRoch) {
     sch[e1] = 6;
     sch[g1] = 0;
     sch[h1] = 4;
     sch[f1] = 0;
     if (zobrazPole != null) {
       zobrazPole.zobrazPole(e1);
       zobrazPole.zobrazPole(f1);
       zobrazPole.zobrazPole(g1);
       zobrazPole.zobrazPole(h1);
     }
     return;
   }
  
   /*Velka bila rosada*/
   if (tah == VBRoch) {
     sch[e1] = 6;
     sch[c1] = 0;
     sch[a1] = 4;
     sch[d1] = 0;
     if (zobrazPole != null) {
       zobrazPole.zobrazPole(e1);
       zobrazPole.zobrazPole(d1);
       zobrazPole.zobrazPole(c1);
       zobrazPole.zobrazPole(a1);
     }
     return;
   }
   
  /*Mala cerna rosada*/
   if (tah==MCRoch) {
     sch[e8] = -6;
     sch[g8] = 0;
     sch[h8] = -4;
     sch[f8] = 0;
     if (zobrazPole != null) {
       zobrazPole.zobrazPole(e8);
       zobrazPole.zobrazPole(f8);
       zobrazPole.zobrazPole(g8);
       zobrazPole.zobrazPole(h8);
     }
     return;
   }
   /*Velka cerna rosada*/
   if (tah == VCRoch) {
     sch[e8] = -6;
     sch[c8] = 0;
     sch[a8] = -4;
     sch[d8] = 0;
     if (zobrazPole != null) {
       zobrazPole.zobrazPole(e8);
       zobrazPole.zobrazPole(d8);
       zobrazPole.zobrazPole(c8);
       zobrazPole.zobrazPole(a8);
     }
     return;
   }
  
   /*Promena bileho pesce*/
  if ((tah>>12)==12)
   {odkud=a7+((tah>>7)&7);
    kam=a8+((tah>>4)&7);
    sch[odkud]=1;
    sch[kam]=z.brani;
    return;
   }

   /*Promena cerneho pesce*/
  if ((tah>>12)==13)
   {
    odkud=a2+((tah>>7)&7);
    kam=a1+((tah>>4)&7);
    sch[odkud]=-1;
    sch[kam]=z.brani;
    return;
   }
  /* Brani mimochodem (nic jineho to uz byt nemuze)*/
  tah&=0x3fff; /* odstraneni prvnich dvou bitu, aby se lepe siftovalo*/
  kam=(tah&127);
  odkud=(tah>>7);
  sch[kam]=0;
  if(odkud<kam)
   {sch[kam-10]=-1; /* to hraje bily*/
    sch[odkud]=1;
    }
  else
  {sch[kam+10]=1;  /* cerny */
   sch[odkud]=-1;
   }
  } 
  
  public void tahni(int tah, boolean globalne,
      boolean ukousniKonec, ZobrazPole zobrazPole) {
    
     int odkud,kam;
     byte co;
     
     push(globalne, ukousniKonec, tah);
     mimoch = 0; /*Vetsina tahu neni pescem o 2, pokud ano, osetri se*/
     bily = !bily;
     
     if ((tah>>15) == 0) /* Normalni tah*/
      {kam = tah&127;
       odkud = tah>>7;
       if (/* bud cerny tahne pescem o 2*/
         odkud - kam == 20 && sch[odkud] == -1
                   /* a bily pesec ciha */
           && (sch[kam + 1] == 1 || sch[kam - 1] == 1)
           /* nebo bily tahne pescem o 2 */
           || odkud - kam == -20 && sch[odkud] == 1
                   /* a cerny pesec ciha */
           && (sch[kam + 1] == -1 || sch[kam - 1] == -1))

         mimoch = (byte) kam;
    /* Niceni rosad
      Pozn.: nejde dat vsude 'else', protoze napr. Va1xa8 nici obe velke rosady*/
       if (odkud == e1) roch &= 12; else /* 1100b*/
       if (odkud == e8) roch &= 3; else /* 0011b*/
     { if (kam == a1 || odkud == a1) roch &= 13;/*1101b*/
       if (kam == h1 || odkud == h1) roch &= 14;/*1110b*/
       if (kam == a8 || odkud == a8) roch &= 7; /*0111b*/
       if (kam == h8 || odkud == h8) roch &= 11;/*1011b*/
     }
   /* Ulozim si sebranou figuru*/
    push(sch[kam], globalne);
    /* zakladni rutina normalniho tahu:*/
    sch[kam] = sch[odkud];
    sch[odkud] = 0;
    if (zobrazPole != null) {
      zobrazPole.zobrazPole(odkud);
      zobrazPole.zobrazPole(kam);
    }
    return;
    }
     /* Nenormalni tah

        Mala bila rosada*/
     if (tah == MBRoch)
      {sch[e1]=0;
       sch[g1]=6;
       sch[h1]=0;
       sch[f1]=4;
       roch&=12;
       push((byte)0, globalne);
       if (zobrazPole != null) {
         zobrazPole.zobrazPole(e1);
         zobrazPole.zobrazPole(f1);
         zobrazPole.zobrazPole(g1);
         zobrazPole.zobrazPole(h1);
       }
       return;}
     /*Velka bila rosada*/
     if (tah == VBRoch)
      {sch[e1]=0;
       sch[c1]=6;
       sch[a1]=0;
       sch[d1]=4;
       roch&=12;
       push((byte)0, globalne);
       if (zobrazPole != null) {
         zobrazPole.zobrazPole(a1);
         zobrazPole.zobrazPole(c1);
         zobrazPole.zobrazPole(d1);
         zobrazPole.zobrazPole(e1);
       }
       return;}
    /*Mala cerna rosada*/
     if (tah==MCRoch)
      {sch[e8]=0;
       sch[g8]=-6;
       sch[h8]=0;
       sch[f8]=-4;
       roch&=3;
       push((byte)0, globalne);
       if (zobrazPole != null) {
         zobrazPole.zobrazPole(e8);
         zobrazPole.zobrazPole(f8);
         zobrazPole.zobrazPole(g8);
         zobrazPole.zobrazPole(h8);
       }
       return;}
     /*Velka cerna rosada*/
     if (tah==VCRoch)
      {sch[e8]=0;
       sch[c8]=-6;
       sch[a8]=0;
       sch[d8]=-4;
       roch&=3;
       push((byte)0, globalne);
       if (zobrazPole != null) {
         zobrazPole.zobrazPole(a8);
         zobrazPole.zobrazPole(c8);
         zobrazPole.zobrazPole(d8);
         zobrazPole.zobrazPole(e8);
       }
       return;}
     /*Promena bileho pesce*/
    if ((tah>>12)==12)
     {odkud=(a7+((tah>>7)&7));
      kam=(a8+((tah>>4)&7));
      co=(byte)(2+((tah>>10)&3));
      /* Ulozim si, co jsem sebral */
      push(sch[kam], globalne);
      sch[odkud]=0;
      sch[kam]=co;
      if (kam==a8) /* meni pesce na a8, mohl sezrat vez => rosady...*/
       roch&=7; /*0111b*/
      if (kam==h8) /* meni pesce na h8, mohl sezrat vez => rosady...*/
       roch&=11; /*1011b */
      if (zobrazPole != null) {
        zobrazPole.zobrazPole(odkud);
        zobrazPole.zobrazPole(kam);
      }
      return;
     }
     /*Promena cerneho pesce*/
    if ((tah>>12)==13)
     {
     odkud=(byte)(a2+((tah>>7)&7));
      kam=(byte)(a1+((tah>>4)&7));
      co=(byte)(-(2+((tah>>10)&3)));

      /* Ulozim si, co jsem sebral */
      push(sch[kam], globalne);
      sch[odkud]=0;
      sch[kam]=co;
      if (kam==a1) /* meni pesce na a1, mohl sezrat vez => rosady...*/
        roch&=13; /*1101b*/
       if (kam==h1) /* meni pesce na h1, mohl sezrat vez => rosady...*/
        roch&=14; /*1110b*/
       if (zobrazPole != null) {
         zobrazPole.zobrazPole(odkud);
         zobrazPole.zobrazPole(kam);
       }
       return;
      }
     /* Brani mimochodem (nic jineho to uz byt nemuze)*/
     tah&=0x3fff; /* odstraneni prvnich dvou bitu, aby se lepe siftovalo*/
     kam=(tah&127);
     odkud=(tah>>7);
     if(odkud<kam)
      {sch[kam-10]=0;
       push((byte)-1, globalne);
     }
       /* to hral bily*/
     else
      {sch[kam+10]=0;
      push((byte)1, globalne);
     }
       /* cerny*/
     sch[kam] = sch[odkud];
     sch[odkud] = 0;
     if (zobrazPole != null) {
       zobrazPole.zobrazPole(odkud);
       zobrazPole.zobrazPole(kam);
       zobrazPole.zobrazPole(kam + 10);
       zobrazPole.zobrazPole(kam - 10);
     }
     return;
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
      for(int q = p + ofsety[j]; sch[q] <= 0; q += ofsety[j]) {
        zaradTah(tahy, p, q);
        if (sch[q] < 0) {
          break;
        }
      }
    }
  }
  
  private void dlouhaCernaFigura(Vector tahy, int o1, int o2, int p) {
    for(int j = o1; j <= o2; j++) {
      for(int q = p + ofsety[j]; sch[q] >= 0 && sch[q] < 7; q += ofsety[j]) {
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
  
  private boolean ohrozeno(int i, boolean bilym) /*bilym - ohrozuje to pole bily*/
   {
    int j,k;
    if (bilym) {
      if (sch[i - 9] == 1) return true;/*pescem*/
      if (sch[i - 11] == 1) return true;/*pescem*/
      for (j = 8; j <= 15; j++)
        if (sch[i + ofsety[j]] == 2) return true;/*konem*/
      for(j = 0; j <= 7; j++)
        if (sch[i + ofsety[j]] == 6) return true;/*kralem*/
      for (j = 0; j <= 3; j++) /*vezi nebo damou po rade/sloupci*/
       {
        k = ofsety[j];
        while(true) {
          if ((sch[i + k] == 4) || sch[i + k] == 5)
            return true;
          if (sch[i + k] != 0) break;
          k+=ofsety[j];
        }
      }
      for(j = 4; j <= 7; j++) /*strelcem nebo damou po diagonale*/
       {
        k = ofsety[j];
        while (true) {
          if ((sch[i + k] == 3) || (sch[i + k] == 5)) return true;
          if (sch[i + k] != 0) break;
          k+=ofsety[j];
        }
      }
    } else /*cernym*/
    {
      if (sch[i + 9] == -1) return true;/*pescem*/
      if (sch[i + 11] == -1) return true;/*pescem*/
      for(j = 8; j <= 15; j++)
        if (sch[i + ofsety[j]] == -2) return true;/*konem*/
      for (j = 0; j <= 7; j++)
        if (sch[i + ofsety[j]] == -6) return true;/*kralem*/
        for (j = 0; j <= 3; j++) /*vezi nebo damou po rade/sloupci*/
         {
          k = ofsety[j];
          while (true) {
            if (sch[i + k] == -4 ||
                sch[i + k] == -5) return true;
            if (sch[i + k] != 0) break;
            k+=ofsety[j];
          }
        }
        for (j = 4; j <= 7; j++) /*vezi nebo damou po rade/sloupci*/
        {
         k = ofsety[j];
         while (true) {
           if (sch[i + k] == -3 ||
               sch[i + k] == -5) return true;
           if (sch[i + k] != 0) break;
           k+=ofsety[j];
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
  
  public Vector nalezTahy() {
    Vector tahy = nalezPseudolegalniTahy2();
    Vector t = new Vector();
    Iterator i = tahy.iterator();
    while (i.hasNext()) {
      int tah = ((Integer) i.next()).intValue();
      tahni(tah, false, false, null);
      if (!sach(!bily)) {
        t.add(new Integer(tah));
      }
      tahniZpet(tah, false, null);
    }
    tahy = t;
    return t;
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
          if (sch[i + 11] != 0) for(j=3;j>=0;j--) zaradBilouPromenu(tahy, i, i + 11, j);
          if (sch[i + 9] != 0) for(j=3;j>=0;j--) zaradBilouPromenu(tahy, i, i + 9, j);
         }
      break;
     case 2: /* kun*/
      for (j = 8; j <= 15; j++)
        if ((sch[i + ofsety[j]]) <=0 ) zaradTah(tahy, i,i + ofsety[j]);
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
         if ((sch[i + ofsety[j]]) <= 0) zaradTah(tahy, i, i + ofsety[j]);
       if (i == e1 && ((roch & 1) != 0) && (sch[i + 1] == 0) && (sch[i + 2] == 0)
         && !ohrozeno(i + 1, false) && !ohrozeno(i, false))  {
         zaradRosadu(tahy, MBRoch);
       }
       if (i == e1 && ((roch & 2) != 0) && (sch[i - 1] == 0) && (sch[i - 2] == 0)
         && !ohrozeno(i - 1, false) && !ohrozeno(i, false))  {
         zaradRosadu(tahy, VBRoch);
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
        if (sch[i + ofsety[j]] >=0 && sch[i + ofsety[j]]  <7)
          zaradTah(tahy, i, i + ofsety[j]);
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
         if (sch[i + ofsety[j]] >= 0 && sch[i + ofsety[j]] < 7)
           zaradTah(tahy, i, i + ofsety[j]);
       if (i == e8 && (roch & 4) != 0 && sch[f8] == 0 && sch[g8] == 0
           && !ohrozeno(e8, true) && !ohrozeno(f8, true)) {
         zaradRosadu(tahy, MCRoch);
       }
       if (i == e8 && (roch & 8) != 0 && sch[d8] == 0 && sch[c8] == 0
         && !ohrozeno(e8, true) && !ohrozeno(d8, true)){
       zaradRosadu(tahy, VCRoch);
      }
      break;
     }/* od switch*/
    } /* od for cyklu*/
    } /* od hraje cerny*/
    return tahy;
  }
  
  
  public static int abs(int i) {
    if (i < 0) return -i;
    return i;
  }
  
  private static final int J_Nic = 0;
  private static final int J_Radka = 1;
  private static final int J_Sloupec = 2;
  
  /* Je tah urcen jednoznacne ? (urcen je J_xxx) a tahy v uloze jsou nalezene */
  private boolean jednoZnacny(Vector tahy, int tah, int urcen)
   {int odkud, kam;

   if (tah>>14 != 0) return true;/* Nenormalni tah je vzdy jednoznacny*/
    kam=tah&127;
    odkud=tah>>7;

    Iterator i = tahy.iterator();

    while (i.hasNext()) {
     int t = ((Integer)(i.next())).intValue();    
     if (((t>>14) == 0)&&        /* normalni tah */
         ((t&127)==kam)&&     /* na stejne policko */
         (odkud!=(t>>7)) &&   /* z jineho policka */
         ((sch[odkud])==(sch[t>>7])) /* stejnou figurkou */
         )
        switch(urcen){
          case J_Nic: return false;
          case J_Radka: if(odkud/10==(t>>7)/10)return false; break;
          case J_Sloupec: if(odkud%10==(t>>7)%10)return false; break;
        }
    }
    return true;
   } 
  
  public String tah2Str(Vector tahy, int tah) {
    int odkud, kam;
    StringBuffer s = new StringBuffer();

    if ((tah >> 14) == 0) /* Normalni tah*/ {
      kam = tah & 127;
      odkud = tah >> 7;
      //i = 0;
    switch (abs(sch[odkud])) {
      case 1:
      if (sch[kam] != 0) s.append((char)('a' + (odkud - a1) % 10));
      break;
      case 2: s.append('J'); break;
      case 3: s.append('S'); break;
      case 4: s.append('V'); break;
      case 5: s.append('D'); break;
      case 6: s.append('K'); break;
    }
   if (abs(sch[odkud]) != 1) {
     if (!jednoZnacny(tahy, tah, J_Nic)) /* Zkusim Da1 */
    {    /* Tak Dha1 */
      if (jednoZnacny(tahy, tah, J_Sloupec)) s.append((char)((odkud-a1)%10 + 'a'));
       else /* Tak D1a1 */
        if (jednoZnacny(tahy, tah, J_Radka)) s.append((char)((odkud-a1)/10 + '1'));
        else /* Tak teda Dh1a1 (nutne pokud jsou 3 damy na h1, h8 a a8)*/
         {
           s.append((char)((odkud-a1)%10 + 'a'));
           s.append((char)((odkud-a1)/10 + '1'));
         }
     }
   }
   if (sch[kam] != 0) s.append('x');
   s.append((char)((kam-a1)%10 + 'a'));
   s.append((char)((kam-a1)/10 + '1'));
     
    return new String(s);
  }
  /* Nenormalni tah
     Mala rosada*/
  if (tah==MBRoch || tah==MCRoch) return "O-O";
  /*Velka rosada*/
  if (tah==VBRoch || tah==VCRoch) return "O-O-O";

  /*Promena bileho pesce*/
 if ((tah>>12)==12 || (tah>>12)==13 ) {
 if ((tah>>12)==12)
  {odkud=a7+((tah>>7)&7);
   kam=a8+((tah>>4)&7);}
  else
  {odkud=a2+((tah>>7)&7);
   kam=a1+((tah>>4)&7);}
   s.append((char)((odkud-a1)%10 + 'a'));
   if (sch[kam] != 0) {s.append('x'); s.append((char)((kam-a1)%10 + 'a'));}
   s.append((char)((kam-a1)/10 + '1'));
   switch((tah>>10)&3){
   case 0: s.append('J'); break;
   case 1: s.append('S'); break;
   case 2: s.append('V'); break;
   case 3: s.append('D'); break;
   }
   return new String(s);
  }
 /* Brani mimochodem (nic jineho to uz byt nemuze)*/
 tah&=0x3fff; /* odstraneni prvnich dvou bitu, aby se lepe siftovalo*/
 kam=tah&127;
 odkud=tah>>7;
 s.append((char)((odkud-a1)%10 + 'a'));
/* s[i++]=(odkud-a1)/10 + '1';*/
 s.append('x');
 s.append((char)((kam-a1)%10 + 'a'));
 s.append((char)((kam-a1)/10 + '1'));
 s.append(' ');
 s.append('e');
 s.append('.');
 s.append('p');
 s.append('.');
 return new String(s);
} 
  
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

  public static final int MBRoch = (7<<13);
  public static final int VBRoch = ((7<<13)|(1<<11));
  public static final int MCRoch = (15<<12);
  public static final int VCRoch = (31<<11);

  
  /**
   * Je mezi nalezenymi tahi nejaky vedouci z odkud ?
   * @param odkud
   * @return
   */
  public boolean JeTam1(Vector tahy, int odkud) {
    //int p, k;
    
    Iterator ti = tahy.iterator(); 
    while (ti.hasNext()) {
      int t = ((Integer)ti.next()).intValue(); 
      if((t>>15) == 0) /* Normalni tah*/  if  (odkud==t>>7)  return true; else continue;
      if ((t==MBRoch || t==VBRoch)) if (odkud==e1) return true; else continue;
      if ((t==MCRoch || t==VCRoch)) if (odkud==e8) return true; else continue;
    /*Promena bileho pesce*/
      if ((t>>12)==12) if (odkud == a7+((t>>7)&7)) return true; else continue;
    /*Promena cerneho pesce*/
      if ((t>>12)==13) if (odkud == a2+((t>>7)&7)) return true; else continue;
    /* Brani mimochodem (nic jineho to uz byt nemuze)*/
      if ((t&0x3fff)>>7 == odkud) return true; else continue;
    }
    return false;
  }

  public boolean JeTam2(Vector tahy, int odkud, int kam) {
    Iterator ti = tahy.iterator(); 
    while (ti.hasNext()) {
      int t = ((Integer)ti.next()).intValue();
      // Normalni tah
      if((t>>15) == 0)  if(kam==(t&127) && odkud==(t>>7)) return true; else continue;
  /*Ro��dy*/
      if (t==MBRoch) if(odkud==e1 && kam==g1) return true; else continue;
      if (t==VBRoch) if(odkud==e1 && kam==c1) return true; else continue;
      if (t==MCRoch) if(odkud==e8 && kam==g8) return true; else continue;
      if (t==VCRoch) if(odkud==e8 && kam==c8) return true; else continue;
    /*Promena bileho pesce*/
      if ((t>>12)==12)
        if(odkud==a7+((t>>7)&7) && kam==a8+((t>>4)&7)) return true; else continue;
    /*Promena cerneho pesce*/
      if ((t>>12)==13)
        if(odkud==a2+((t>>7)&7) && kam==a1+((t>>4)&7)) return true; else continue;
     /* Brani mimochodem (nic jineho to uz byt nemuze)*/
      t&=0x3fff; /* odstraneni prvnich dvou bitu, aby se lepe siftovalo*/
      if(kam==(t&127) && odkud==(t>>7)) return true; else continue;
    } /* konec while cyklu*/
    return false;
  }
  
  public int DoplnTah(Vector tahy, int odkud, int kam) {
    Iterator ti = tahy.iterator(); 
    while (ti.hasNext()) {
      int t = ((Integer)ti.next()).intValue();
       /* Normalni tah*/
     if((t>>15) == 0)  if(kam==(t&127) && odkud==(t>>7)) return t; else continue;
  /*Ro��dy*/
     if (t==MBRoch) if(odkud==e1 && kam==g1)  return t; else continue;
     if (t==VBRoch) if(odkud==e1 && kam==c1)  return t; else continue;
     if (t==MCRoch) if(odkud==e8 && kam==g8)  return t; else continue;
     if (t==VCRoch) if(odkud==e8 && kam==c8)  return t; else continue;
    /*Promena bileho pesce*/
     if ((t>>12)==12)
       if(odkud==a7+((t>>7)&7) && kam==a8+((t>>4)&7))
         return(t&(0xFFFF^(3<<10)))|(/*MessageBox.messageBox("Prom�na p�ce", "Vyberte figuru", MessageBox.MB_PROMENA3)*/3<<10);
       else continue;
    /*Promena cerneho pesce*/
  if ((t>>12)==13)
   if(odkud==a2+((t>>7)&7) && kam==a1+((t>>4)&7))
    return(t&(0xFFFF^(3<<10)))|(/*MessageBox.messageBox("Prom�na p�ce", "Vyberte figuru", MessageBox.MB_PROMENA)*/3<<10);
   else continue;
  /* Brani mimochodem (nic jineho to uz byt nemuze)*/
  if(kam==(t&0x3fff&127) && odkud==((t&0x3fff)>>7)) return t; else continue;
  } /* konec while cyklu*/
   return 0;
   }
  
  public Vector getTahy2() {
  	return mtahy;
  }
} 