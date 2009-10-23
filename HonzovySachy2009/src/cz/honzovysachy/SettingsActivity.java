package cz.honzovysachy;

import cz.honzovysachy.resouces.S;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
	
	private static int getId(int realId)
	{
		switch (realId) {
		case 3: return R.id.cs;
		case 4: return R.id.en;
		case 2: return R.id.sdcard;
		default: return R.id.localedefault;
		}
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle(S.g("SETTINGS"));
		setContentView(R.layout.settings);
		final Button ok = (Button)findViewById(R.id.ok);
		final RadioGroup group = (RadioGroup)findViewById(R.id.localegroup);
		SharedPreferences pref = getBaseContext().getSharedPreferences(AktivitaSachovnice.SETTINGS, 0);
		RadioButton selected = (RadioButton)findViewById(getId(pref.getInt(AktivitaSachovnice.LOCALE, 1)));
		selected.setChecked(true);
		ok.setText(S.g("OK"));
		RadioButton cs = (RadioButton)findViewById(R.id.cs);
		RadioButton en = (RadioButton)findViewById(R.id.en);
		RadioButton def = (RadioButton)findViewById(R.id.localedefault);
		TextView loc = (TextView)findViewById(R.id.locale);
		cs.setText(S.g("CZECH"));
		en.setText(S.g("ENGLISH"));
		def.setText(S.g("DEFAULT"));
		loc.setText(S.g("LOCALE"));
		
		
		
		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences pref = getBaseContext().getSharedPreferences(AktivitaSachovnice.SETTINGS, 0);
				SharedPreferences.Editor editor = pref.edit();
				int type;
			    editor.putInt(AktivitaSachovnice.LOCALE, type = getRealId(group.getCheckedRadioButtonId()));
                editor.commit();
                S.init(type, AktivitaSachovnice.LOCALE_FILE);
                AktivitaSachovnice.mChangedLanguage = true;
				finish();				
			}
			
		});
	}
}
