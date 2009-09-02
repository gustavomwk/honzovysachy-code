package cz.sachy.pravidla;

import java.io.Serializable;
import java.util.Vector;

public class SavedTask implements Serializable {

	private static final long serialVersionUID = 6400963755210507192L;
	/** End of game type */
	public int mEnd;
	/** Moves of the game */
	public Vector mGame;
	/** Index in the game. (Was a move returned?) */
	public int mIndexInGame;
	/** Current board */
	public Pozice mBoard;
	
	public SavedTask(int end, Vector game, int indexInGame, Pozice board) {
		mEnd = end;
		mGame = game;
		mIndexInGame = indexInGame;
		mBoard = board;
	}
}
