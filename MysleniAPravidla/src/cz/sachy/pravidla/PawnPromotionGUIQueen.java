package cz.sachy.pravidla;

public class PawnPromotionGUIQueen implements PawnPromotionGUI {

	public static final PawnPromotionGUIQueen gui = new PawnPromotionGUIQueen();
	@Override
	public int promotion() {
		return 3;
	}
	
}
