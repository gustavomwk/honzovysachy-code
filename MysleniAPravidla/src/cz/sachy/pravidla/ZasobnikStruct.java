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

import cz.sachy.mysleni.HodnotaPozice;

public class ZasobnikStruct {
	byte mRoch;
	byte mMimoch;
	public int mTah;
	byte mBrani;
	short mZmena;

	short mExtend;
	byte mExtendType;
	int mHashF;
	public short mBm;
	public short mCm;
	byte mKam;
	byte mBk;
	byte mCk;
	//int mValue;

	public ZasobnikStruct(Task t) {
		set(t);
	}
	
	public void set(Task t)  {
		Pozice p = t.board;
		mRoch = p.roch;
		mMimoch = p.mimoch;
		mTah = 0;
		mBrani = 0;
		mZmena = 0;
		mExtend = 0;
		mExtendType = 0;
		mHashF = t.mHashF.hash(p);
		mBm = HodnotaPozice.bm(p);
		mCm = HodnotaPozice.cm(p);

		if (t.mIndexVPartii <= 0)
			mKam = 0;
		else
			mKam = ((ZasobnikStruct)t.mPartie.elementAt(t.mIndexVPartii)).mKam;
		mBk = mCk = 0;
		// Find position of white and black kings
		for (byte i = Pozice.a1; i <= Pozice.h8; i++)
		{
			if (p.sch[i] == 6) {
				mBk = i;
				if (mCk != 0) break;
			}
			if (p.sch[i] == -6) {
				mCk = i;
				if (mBk != 0) break;
			}
		}
		
	}

	public ZasobnikStruct(byte roch, byte mimoch, int tah) {
		set(roch, mimoch, tah);
	}

	public void set(byte roch, byte mimoch, int tah) {
		mRoch = roch;
		mMimoch = mimoch;
		mTah = tah;
	}
}
