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

public class ZasobnikStruct {
  byte roch;
  byte mimoch;
  public int tah;
  byte brani;
  
  public ZasobnikStruct(byte roch, byte mimoch, int tah) {
    set(roch, mimoch, tah);
  }
  
  public void set(byte roch, byte mimoch, int tah) {
    this.roch = roch;
    this.mimoch = mimoch;
    this.tah = tah;
  }
}
