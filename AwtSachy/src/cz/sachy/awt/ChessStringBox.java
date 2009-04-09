package cz.sachy.awt;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

import cz.sachy.mysleni.ThinkingOutput;

@SuppressWarnings("serial")
public class ChessStringBox extends Panel implements ThinkingOutput {
	Label mDepth;
	ChessStringBox() {
		Label bDepth = new Label("Depth");
		bDepth.setFont(new Font("Dialog", Font.BOLD, 12));
		setLayout(new GridLayout());
		add(bDepth);
		add(mDepth = new Label(""));
	}

	@Override
	public void depth(int depth) {
		mDepth.setText("" + depth);		
	}
}
