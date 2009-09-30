package cz.honzovysachy.pravidla;

import java.io.Serializable;

public class PGNHeaderData implements Serializable {

	private static final long serialVersionUID = -8930213337493051532L;
	
	public String mFileName; 
	public String mWhite;
	public String mBlack;
	public String mEvent;
	public int mWhiteElo;
	public int mBlackElo;
	public int mResult;
	
	public PGNHeaderData(String fileName, String white, String black, String event, int whiteElo,
			int blackElo, int result)
	{
		mFileName = fileName;
		mWhite = white;
		mBlack = black;
		mEvent = event;
		mWhiteElo = whiteElo;
		mBlackElo = blackElo;
		mResult = result;
	}

}
