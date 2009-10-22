package cz.honzovysachy;

import cz.honzovysachy.resouces.S;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class SettingsActivity extends Activity {
	private static int getRealId(int id)
	{
		switch (id) {
		case R.id.cs: return 3;
		case R.id.en: return 4;
		case R.id.localedefault: return 1;
		case R.id.sdcard: return 2;
		default: return 1;
		}
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle(S.g("SETTINGS"));
		setContentView(R.layout.settings);
		final Button ok = (Button)findViewById(R.id.ok);
		final RadioGroup group = (RadioGroup)findViewById(R.id.localegroup);
		ok.setText(S.g("OK"));
		
		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences pref = getBaseContext().getSharedPreferences("locale", 0);
				SharedPreferences.Editor editor = pref.edit();
				int type;
			    editor.putInt("locale", type = getRealId(group.getCheckedRadioButtonId()));
                editor.commit();
                S.init(type, "/sdcard/strings_hs.txt");
				finish();				
			}
			
		});
	}
}
