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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent changeSE = new Intent(Settings.this, FirstLaunch.class);
				Settings.this.startActivity(changeSE);
			}
		});
	}
}
