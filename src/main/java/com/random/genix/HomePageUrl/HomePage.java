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

package com.random.genix.HomePageUrl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class HomePage {
	public HomePage() {

	}

	public String getHomePageUrl(Context context) {
		String home = "";
		if (context.getSharedPreferences("prefs", context.MODE_APPEND).getString("engine", "") != null) {
			home = context.getSharedPreferences("prefs", context.MODE_APPEND).getString("engine", "");
		}
		if (home == "") {
			home = "https://www.google.com/";
		}
		return home;
	}
}
