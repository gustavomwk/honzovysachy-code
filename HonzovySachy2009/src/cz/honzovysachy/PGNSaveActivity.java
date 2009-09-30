package cz.honzovysachy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PGNSaveActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.pgn_header);
        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent result = new Intent();
                result.putExtra("PGNHeader", new PGNHeaderData("/sdcard/file.pgn", "Nemec, Jan", "Sachy, Honzovy", "Nuda ve vlaku", 1990, 1530, 1));
                setResult(10, result);
            }
        });
        
        setResult(10, null);
        
        
	}
}
