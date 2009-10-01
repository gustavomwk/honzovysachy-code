package cz.honzovysachy.pravidla;

import java.io.Serializable;

public class PGNHeaderData implements Serializable {

	private static final long serialVersionUID = -8930213337493051532L;
	
	public String mFileName; 
	public String mWhite;
	public String mBlack;
	public String mEvent;
	public int mRound;
	public int mWhiteElo;
	public int mBlackElo;
	public int mResult;
	
	public PGNHeaderData(String fileName, String white, String black, String event, int round, int whiteElo,
			int blackElo, int result)
	{
		mFileName = fileName;
		mWhite = white;
		mBlack = black;
		mEvent = event;
		mRound = round;
		mWhiteElo = whiteElo;
		mBlackElo = blackElo;
		mResult = result;
	}
	
	public static final String[] RESULTS = {"*", "1-0", "0-1", "1/2-1/2"};
}
