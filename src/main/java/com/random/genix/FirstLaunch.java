/*
Copyright (C) 2022 GenixBrowser0

This file is part of GenixBrowser0.

GenixBrowser0 is free software:
you can redistribute it and/or modify it under the terms of
the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

GenixBrowser0 is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY;
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with GenixBrowser0.
If not, see <https://www.gnu.org/licenses/>.
*/

package com.random.genix;

import android.widget.Toast;
import com.random.genix.HomePageUrl.HomePage;
import com.random.genix.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class FirstLaunch extends AppCompatActivity {
	SharedPreferences prefs;
	String x = "";
	RadioButton radio, radio1, radio2, radio3, radio4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_launch);
		radio = (RadioButton) findViewById(R.id.radio);
		radio1 = (RadioButton) findViewById(R.id.radio1);
		radio2 = (RadioButton) findViewById(R.id.radio2);
		radio3 = (RadioButton) findViewById(R.id.radio3);
		radio4 = (RadioButton) findViewById(R.id.radio4);
		
		RadioGroup radioattend = (RadioGroup) findViewById(R.id.radioattendgroup);

		prefs = getSharedPreferences("prefs", MODE_PRIVATE);

		radioattend.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				switch (i) {
				case R.id.radio:
					x = "https://www.startpage.com/";
					prefs.edit().putString("engine", x).commit();
					proceed();
					break;
				case R.id.radio1:
				    x = "https://www.google.com/";
					prefs.edit().putString("engine", x).commit();
					proceed();
					break;
				case R.id.radio2:
					x = "https://www.bing.com/";
					prefs.edit().putString("engine", x).commit();
					proceed();
					break;
				case R.id.radio3:
					x = "https://start.duckduckgo.com/";
					prefs.edit().putString("engine", x).commit();
					proceed();
					break;
				case R.id.radio4:
				    x ="https://search.brave.com/";
					prefs.edit().putString("engine", x).commit();
					proceed();
					break;
				default:
					break;
				}
			}
		});
	}

    public void proceed() {
		if (x != "") {
			prefs.edit().putString("url", new HomePage().getHomePageUrl(FirstLaunch.this.getApplicationContext())).commit();
			Toast.makeText(getApplicationContext(), "Succesfully changed your search engine.", Toast.LENGTH_SHORT).show();
		}
	}
}
