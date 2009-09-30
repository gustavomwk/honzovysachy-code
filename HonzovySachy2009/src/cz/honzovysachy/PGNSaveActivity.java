package cz.honzovysachy;

import cz.honzovysachy.pravidla.PGNHeaderData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PGNSaveActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.pgn_header);
        Button save = (Button)findViewById(R.id.save);
        final EditText filename = (EditText)findViewById(R.id.filename);
        final EditText white = (EditText)findViewById(R.id.white);
        final EditText black = (EditText)findViewById(R.id.black);
        final EditText event = (EditText)findViewById(R.id.event);
        final EditText whiteelo = (EditText)findViewById(R.id.whiteelo);
        final EditText blackelo = (EditText)findViewById(R.id.blackelo);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent result = new Intent();
            	int iWhiteElo = 1550;
            	try {
            		iWhiteElo = Integer.parseInt(whiteelo.getText().toString());
            	} catch (NumberFormatException e) {};
            	int iBlackElo = 1550;
            	try {
            		iBlackElo = Integer.parseInt(blackelo.getText().toString());
            	} catch (NumberFormatException e) {};
                result.putExtra("PGNHeader", 
                		new PGNHeaderData(
                				filename.getText().toString(),
                				white.getText().toString(),
                				black.getText().toString(),
                				event.getText().toString(),
                				iWhiteElo,
                				iBlackElo, 1));
                setResult(10, result);
            }
        });
        
        setResult(10, null);
        
        
	}
}
